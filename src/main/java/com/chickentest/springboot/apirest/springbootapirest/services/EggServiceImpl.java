package com.chickentest.springboot.apirest.springbootapirest.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chickentest.springboot.apirest.springbootapirest.dao.IChickenDao;
import com.chickentest.springboot.apirest.springbootapirest.dao.IEggDao;
import com.chickentest.springboot.apirest.springbootapirest.models.Chicken;
import com.chickentest.springboot.apirest.springbootapirest.models.Egg;

@Service
public class EggServiceImpl implements IEggService {

	@Autowired
	IEggDao eggDao;
	
	@Autowired
	IChickenDao chickenDao;
		
	@Override
	public List<Egg> findAll() {
		return (List<Egg>) eggDao.findAll();
	}

	@Override
	public Egg findById(Long Id) {
		return eggDao.findById(Id).orElse(null);
	}

	@Override
	public Egg save(Egg egg) {
		return eggDao.save(egg);
	}
	
	@Override
	public Egg update(Long Id, Egg egg) {		
		Egg currentEgg = eggDao.findById(Id).orElse(null);
		currentEgg.setPrice(egg.getPrice());
		currentEgg.setDays(egg.getDays());
		currentEgg.setFarm(egg.getFarm());
		return eggDao.save(currentEgg);
	}

	@Override
	public String delete(Long Id) {
		eggDao.deleteById(Id);
		return "The egg has been deleted";
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
			newChickens.add(new Chicken(eachEgg.getFarm().getChickenPrice(), daysSinceBorn, daysSinceBorn, eachEgg.getFarm()));		
		}
		chickenDao.saveAll(newChickens);	
		eggDao.deleteAll(eggsToChickens);		
	}	
}