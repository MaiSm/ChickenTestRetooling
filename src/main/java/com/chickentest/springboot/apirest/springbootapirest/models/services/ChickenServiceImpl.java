package com.chickentest.springboot.apirest.springbootapirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chickentest.springboot.apirest.springbootapirest.models.dao.IChickenDao;
import com.chickentest.springboot.apirest.springbootapirest.models.entities.Chicken;

@Service
public class ChickenServiceImpl implements IChickenService {

	@Autowired
	IChickenDao chickenDao;
	
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

}
