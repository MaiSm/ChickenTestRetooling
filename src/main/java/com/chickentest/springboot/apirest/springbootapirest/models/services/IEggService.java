package com.chickentest.springboot.apirest.springbootapirest.models.services;

import java.util.List;

import com.chickentest.springboot.apirest.springbootapirest.models.entities.Egg;


public interface IEggService {

	public List<Egg> findAll();
	public Egg findById(Long Id);
	public Egg save(Egg egg);
	public Egg update(Long Id, Egg egg);
	public String delete(Long Id);
}
