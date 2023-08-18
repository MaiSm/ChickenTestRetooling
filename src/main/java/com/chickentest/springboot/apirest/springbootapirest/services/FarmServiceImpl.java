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
	public void buyChickens(Farm farm, int amount) {
		double expendedMoney = farm.getBuyingChickenPrice()*amount;
		farm.setMoney(farm.getMoney()-expendedMoney);		
		chickenService.buyChickens(farm, amount);	
		farmDao.save(farm);
	}
	
}