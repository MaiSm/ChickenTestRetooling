package com.chickentest.springboot.apirest.springbootapirest.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chickentest.springboot.apirest.springbootapirest.models.dao.IFarmDao;
import com.chickentest.springboot.apirest.springbootapirest.models.entities.Farm;

@Service
public class IFarmServiceImpl implements IFarmService{

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
	public Farm save(Farm farm) {
		return farmDao.save(farm);
	}

	@Override
	public Farm update(Long id, Farm farm) {
		Farm currentFarm = farmDao.findById(id).orElse(null);
		currentFarm.setMoney(farm.getMoney());
		currentFarm.setEggs(farm.getEggs());
		currentFarm.setChickens(farm.getChickens());
		currentFarm.setLimitOfEggs(farm.getLimitOfEggs());
		currentFarm.setLimitOfChickens(farm.getLimitOfChickens());
		return farmDao.save(currentFarm);
	}

	@Override
	public String delete(Long id) {
		farmDao.deleteById(id);
		return "The farm has been eliminated";
	}

	@Override
	public void addDays(int days) {
		chickenService.growChickens(days);	
		eggService.growEggs(days);
		chickenService.putEggs(days);
	}
}