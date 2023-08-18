package com.chickentest.springboot.apirest.springbootapirest.services;

import java.util.List;

import com.chickentest.springboot.apirest.springbootapirest.models.Chicken;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;


public interface IChickenService {

	public List<Chicken> findAll();
	public Chicken findById(Long id);
	public Chicken save(Chicken chicken);
	public Chicken update(Long id, Chicken chicken);
	public String delete(Long id);
	public void growChickens (int days);
	public void putEggs(int days);
	public void buyChickens(Farm farm, int amount);
}