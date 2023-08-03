package com.chickentest.springboot.apirest.springbootapirest.models.services;

import java.util.List;
import com.chickentest.springboot.apirest.springbootapirest.models.entities.Farm;

public interface IFarmService {
	
	public List<Farm> findAll();
	public Farm findById(Long id);
	public Farm update(Long id, Farm farm);
	public void addDays(int days);
}