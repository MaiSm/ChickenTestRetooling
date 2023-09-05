package com.chickentest.springboot.apirest.springbootapirest.services;

import java.util.List;
import com.chickentest.springboot.apirest.springbootapirest.models.Chicken;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;


public sealed interface IChickenService permits ChickenServiceImpl {

	public List<Chicken> findAll();
	public void growChickens (int days);
	public void putEggs(int days);
	public void buyChickens(Farm farm, int amount);
	public void sellChickens(Farm farm, int amount, boolean discount);
	public int countChickens(long id);
}