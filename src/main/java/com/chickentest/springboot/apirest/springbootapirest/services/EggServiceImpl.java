package com.chickentest.springboot.apirest.springbootapirest.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chickentest.springboot.apirest.springbootapirest.dao.IChickenDao;
import com.chickentest.springboot.apirest.springbootapirest.dao.IEggDao;
import com.chickentest.springboot.apirest.springbootapirest.dao.IFarmDao;
import com.chickentest.springboot.apirest.springbootapirest.models.Chicken;
import com.chickentest.springboot.apirest.springbootapirest.models.Egg;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;

@Service
public class EggServiceImpl implements IEggService {

	@Autowired
	IEggDao eggDao;
	
	@Autowired
	IChickenDao chickenDao;
	
	@Autowired
	IFarmDao farmDao;
		
	@Override
	public List<Egg> findAll() {
		return (List<Egg>) eggDao.findAll();
	}

	@Override
	public void growEggs(int days) {
		
		List<Egg> eggsToChickens = new ArrayList<>();
		List<Egg> allEggs = (List<Egg>)eggDao.findAll();
		
		for (Egg eachEgg : allEggs) {
			eachEgg.setDays(eachEgg.getDays()+days);			
			if(eachEgg.getDays() < Egg.getDaysToBorn()) {
				eggDao.save(eachEgg);
			}else {
				eggsToChickens.add(eachEgg);
			}
		}		
		eggToChicken(eggsToChickens);			
	}	
		
	public void eggToChicken(List<Egg> eggsToChickens) {
		List<Chicken> newChickens = new ArrayList<>();
		int daysSinceBorn;
		for(Egg eachEgg : eggsToChickens) {	
			daysSinceBorn = eachEgg.getDays()-Egg.getDaysToBorn();
			newChickens.add(new Chicken(eachEgg.getFarm().getSellingChickenPrice(), daysSinceBorn, daysSinceBorn, eachEgg.getFarm()));		
		}
		chickenDao.saveAll(newChickens);	
		eggDao.deleteAll(eggsToChickens);		
	}
	
	@Override
	public void buyEggs(Farm farm, int amount) {
		List<Egg> newEggs = new ArrayList<>();
		for(int i=0; i < amount; i++) {	
			newEggs.add(new Egg(farm.getSellingEggPrice(), Egg.getDaysWhenBoughtEgg(), farm));		
		}
		eggDao.saveAll(newEggs);			
	}
	
	@Override
	public void sellEggs(Farm farm, int amount, boolean discount) {
		List<Egg> allEggs = (List<Egg>) eggDao.findAll();
		List<Egg> eggsToSell = new ArrayList<>();
		double income = Farm.getZero();		
		
		for(int i=0; i < amount; i++) {	
			eggsToSell.add(allEggs.get(i));
			double profit = discount == false ? allEggs.get(i).getPrice() : allEggs.get(i).getPrice()*Farm.getPercentOfDiscount(); 
			income += profit;
		}
		farm.setMoney(farm.getMoney()+income);
		farmDao.save(farm);		
		eggDao.deleteAll(eggsToSell);	
	}
	
	@Override
	public int countEggs(long id) {
		return eggDao.countByFarmId(id);
	}
	
}