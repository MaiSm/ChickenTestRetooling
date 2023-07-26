package com.chickentest.springboot.apirest.springbootapirest.models.services;

import java.util.List;

import com.chickentest.springboot.apirest.springbootapirest.models.entities.Chicken;


public interface IChickenService {

	public List<Chicken> findAll();
	public Chicken findById(Long id);
	public Chicken save(Chicken chicken);
	public Chicken update(Long id, Chicken chicken);
	public String delete(Long id);
	public void growChickens (int days);
	public void putEggs(int days);
}