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
public class ChickenServiceImpl implements IChickenService {

	@Autowired
	IChickenDao chickenDao;	
	
	@Autowired
	IEggDao eggDao;	
	
	@Autowired
	IFarmDao farmDao;
	
	List<Egg> eggsToBePut = new ArrayList<>();
	
	@Override
	public List<Chicken> findAll() {		
		return (List<Chicken>) chickenDao.findAll();
	}

	@Override
	public Chicken findById(Long id) {
		return chickenDao.findById(id).orElse(null);
	}

	@Override
	public Chicken save(Chicken chicken) {
		return chickenDao.save(chicken);
	}
	
	@Override
	public Chicken update(Long id, Chicken chicken) {
		Chicken currentChicken = chickenDao.findById(id).orElse(null);
		currentChicken.setPrice(chicken.getPrice());
		currentChicken.setFarm(chicken.getFarm());
		return chickenDao.save(currentChicken);
	}

	@Override
	public String delete(Long id) {
		chickenDao.deleteById(id);
		return "Chicken has been eliminated";
	}
	
	public void growChickens (int days) {
		
		List<Chicken> allChickens = (List<Chicken>)chickenDao.findAll();
		List<Chicken> chickensToDie = new ArrayList<>();
		
		for (Chicken eachChicken : allChickens) {
			eachChicken.setDays(eachChicken.getDays()+days);
			eachChicken.setDaysSinceLastEggs(eachChicken.getDaysSinceLastEggs()+days);
			if(eachChicken.getDays() < Chicken.getDaysoflife()) {
				chickenDao.save(eachChicken);
			}else {
				chickensToDie.add(eachChicken);
				if(eachChicken.getDaysSinceLastEggs() >= Chicken.getDaysToPutEggs()) {				
					int eggDays = eachChicken.getDaysSinceLastEggs() - Chicken.getDaysToPutEggs();
					for(int i = 0; i<Chicken.getEggsByChicken(); i++) {
						eggsToBePut.add(new Egg(eachChicken.getFarm().getSellingEggPrice(), eggDays, eachChicken.getFarm()));
					}															
				}
			}
		}
		chickenDao.deleteAll(chickensToDie);
	}
	
	public void putEggs(int days) {
		
		List<Chicken> allChickens = (List<Chicken>)chickenDao.findAll(); //where days >0
				
		for (Chicken eachChicken : allChickens) {				
			if(eachChicken.getDaysSinceLastEggs() >= Chicken.getDaysToPutEggs()) {				
				int newCount = eachChicken.getDaysSinceLastEggs() - Chicken.getDaysToPutEggs();
				for(int i = 0; i<Chicken.getEggsByChicken(); i++) {
					eggsToBePut.add(new Egg(eachChicken.getFarm().getSellingEggPrice(), newCount, eachChicken.getFarm()));
				}
				eachChicken.setDaysSinceLastEggs(newCount);											
			}
			chickenDao.save(eachChicken);						
		}		
		eggDao.saveAll(eggsToBePut);
		eggsToBePut.clear(); 
	}
	
	@Override
	public void buyChickens(Farm farm, int amount) {
		List<Chicken> newChickens = new ArrayList<>();
		for(int i=0; i < amount; i++) {	
			newChickens.add(new Chicken(farm.getSellingChickenPrice(), Chicken.getDaysWhenBoughtChicken(), Chicken.getDaysWhenBoughtChicken(), farm));		
		}
		chickenDao.saveAll(newChickens);			
	}
	
	@Override
	public void sellChickens(Farm farm, int amount, boolean discount) {
		List<Chicken> allChickens = (List<Chicken>) chickenDao.findAll();
		List<Chicken> chickensToSell = new ArrayList<>();
		double income = Farm.getZero();		

		for(int i=0; i < amount; i++) {	
			chickensToSell.add(allChickens.get(i));
			double profit = discount == false ? allChickens.get(i).getPrice() : allChickens.get(i).getPrice()*Farm.getPercentOfDiscount(); 
			income += profit;
		}
		farm.setMoney(farm.getMoney()+income);
		farmDao.save(farm);
		chickenDao.deleteAll(chickensToSell);	
	}
	
	@Override
	public int countChickens(long id) {
		return chickenDao.countByFarmId(id);
	}	
	
}