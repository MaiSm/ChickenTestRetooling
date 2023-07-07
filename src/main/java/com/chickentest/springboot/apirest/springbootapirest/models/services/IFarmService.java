package com.chickentest.springboot.apirest.springbootapirest.models.services;

import java.util.List;

import com.chickentest.springboot.apirest.springbootapirest.models.entities.Farm;

public interface IFarmService {
	
	public List<Farm> findAll();
	public Farm findById(Long id);
	public Farm save(Farm farm);
	public Farm update(Long id, Farm farm);
	public String delete(Long id);
}
