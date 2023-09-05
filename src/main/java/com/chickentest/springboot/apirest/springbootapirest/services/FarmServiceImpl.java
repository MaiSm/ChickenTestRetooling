package com.chickentest.springboot.apirest.springbootapirest.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chickentest.springboot.apirest.springbootapirest.dao.IFarmDao;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;

@Service
public final class FarmServiceImpl implements IFarmService{

	@Autowired
	IFarmDao farmDao;
	
	@Autowired
	IChickenService chickenService;	
	
	@Autowired
	IEggService eggService;	
	
	@Override
	public List<Farm> findAll() {
		return (List<Farm>) farmDao.findAll();
	}

	@Override
	public Farm findById(Long id) {
		return farmDao.findById(id).orElse(null);
	}

	@Override
	public Farm update(Long id, Farm farm) {
		Farm currentFarm = farmDao.findById(id).orElse(null);
		currentFarm.setSellingEggPrice(farm.getSellingEggPrice());
		currentFarm.setSellingChickenPrice(farm.getSellingChickenPrice());
		currentFarm.setBuyingEggPrice(farm.getBuyingEggPrice());
		currentFarm.setBuyingChickenPrice(farm.getBuyingChickenPrice());
		currentFarm.setLimitOfChickens(farm.getLimitOfChickens());
		currentFarm.setLimitOfEggs(farm.getLimitOfEggs());

		return farmDao.save(currentFarm);
	}

	@Override
	public void addDays(int days) {
		chickenService.growChickens(days);	
		eggService.growEggs(days);
		chickenService.putEggs(days);
		checkLimits();
	}
	
	@Override
	public void buyChickensOrEggs(Farm farm, int amount, String type) {
		double expendedMoney = type.equals("chickens") ? farm.getBuyingChickenPrice()*amount : farm.getBuyingEggPrice()*amount;
		farm.setMoney(farm.getMoney()-expendedMoney);
		if(type.equals("chickens")) {
			chickenService.buyChickens(farm, amount);
		} else {
			eggService.buyEggs(farm, amount);
		}
		farmDao.save(farm);
	} 

	@Override
	public void sellChickensOrEggs(Farm farm, int amount, String type) {		
		if(type.equals("chickens")) {
			chickenService.sellChickens(farm, amount, false);
		} else {
			eggService.sellEggs(farm, amount, false);
		}
	}

	@Override
	public int countChickensOrEggs(Long id, String type) {
		int numOfChickensOrEggs = 0;
		if (type.equals("chickens")) {
			numOfChickensOrEggs = chickenService.countChickens(id);
		} else if (type.equals("eggs")) {
			numOfChickensOrEggs = eggService.countEggs(id);
		}
		return numOfChickensOrEggs;
	}	
	
	@Override
	public void checkLimits() {
		List<Farm> farmList = (List<Farm>) farmDao.findAll();
		
		for (Farm eachFarm : farmList) {
			int diffOfEggs = eggService.countEggs(eachFarm.getId()) - eachFarm.getLimitOfEggs();
			int diffOfChickens = chickenService.countChickens(eachFarm.getId()) - eachFarm.getLimitOfChickens();

			if (diffOfEggs > Farm.getZero()) {
				eggService.sellEggs(eachFarm, diffOfEggs, true);
			}
			if (diffOfChickens > Farm.getZero()) {
				chickenService.sellChickens(eachFarm, diffOfChickens, true);			
			}
		}
	}
	
}