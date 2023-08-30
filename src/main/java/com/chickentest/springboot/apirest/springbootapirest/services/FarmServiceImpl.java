package com.chickentest.springboot.apirest.springbootapirest.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chickentest.springboot.apirest.springbootapirest.dao.IFarmDao;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;

@Service
public class FarmServiceImpl implements IFarmService{

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

		return farmDao.save(currentFarm);
	}

	@Override
	public void addDays(int days) {
		chickenService.growChickens(days);	
		eggService.growEggs(days);
		chickenService.putEggs(days);
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
		double income = type.equals("chickens") ? farm.getSellingChickenPrice()*amount : farm.getSellingEggPrice()*amount;
		farm.setMoney(farm.getMoney()+income);
		if(type.equals("chickens")) {
			chickenService.sellChickens(farm, amount);
		} else {
			eggService.sellEggs(farm, amount);
		}
		farmDao.save(farm);		
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
	
}