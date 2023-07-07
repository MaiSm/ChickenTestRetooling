package com.chickentest.springboot.apirest.springbootapirest.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chickentest.springboot.apirest.springbootapirest.models.dao.IEggDao;
import com.chickentest.springboot.apirest.springbootapirest.models.entities.Egg;

@Service
public class EggServiceImpl implements IEggService {

	@Autowired
	IEggDao eggDao;
	
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

	

	

}
