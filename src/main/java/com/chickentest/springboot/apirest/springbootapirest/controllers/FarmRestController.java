package com.chickentest.springboot.apirest.springbootapirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chickentest.springboot.apirest.springbootapirest.models.entities.Farm;
import com.chickentest.springboot.apirest.springbootapirest.models.services.IFarmService;

@RestController
public class FarmRestController {

	@Autowired
	IFarmService farmService;
	
	@GetMapping("/farms")
	public List<Farm> findAll() {
		return (List<Farm>) farmService.findAll();
	}
	
	@GetMapping("/farms/{id}")
	public Farm findById(@PathVariable Long id) {
		return farmService.findById(id);
	}
	
	@PostMapping("/farms")
	public Farm create(@RequestBody Farm farm) {
		return farmService.save(farm);
	}
	
	@PutMapping("/farms/{id}")
	public Farm update(@RequestBody Farm farm, @PathVariable Long id) {
		return farmService.update(id, farm);
	}
	
	@DeleteMapping("/farms/{id}")
	public String delete(@PathVariable Long id) {
		return farmService.delete(id);
	}
}
