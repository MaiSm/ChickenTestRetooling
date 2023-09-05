package com.chickentest.springboot.apirest.springbootapirest.services;

import java.util.List;
import com.chickentest.springboot.apirest.springbootapirest.models.Egg;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;


public sealed interface IEggService permits EggServiceImpl {

	public List<Egg> findAll();
	public void growEggs (int days);
	public int countEggs(long id);
	public void buyEggs(Farm farm, int amount);
	public void sellEggs(Farm farm, int amount, boolean discount);
}