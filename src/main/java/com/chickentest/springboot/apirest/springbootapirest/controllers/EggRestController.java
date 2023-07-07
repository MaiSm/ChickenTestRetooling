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

import com.chickentest.springboot.apirest.springbootapirest.models.entities.Egg;
import com.chickentest.springboot.apirest.springbootapirest.models.services.IEggService;

@RestController
public class EggRestController {
	
	@Autowired
	IEggService eggService;
	
	@GetMapping("/eggs")
	public List<Egg> findAll() {
		return eggService.findAll();		
	}

	@GetMapping("/eggs/{id}")
	public Egg findById(@PathVariable Long id){
		return eggService.findById(id);
	}
	
	@PostMapping("/eggs")
	public Egg create(@RequestBody Egg egg) {
		return eggService.save(egg);
	}
	
	@PutMapping("/eggs/{id}")
	public Egg update(@RequestBody Egg egg, @PathVariable Long id) {
		return eggService.update(id, egg);
	}
	
	@DeleteMapping("/eggs/{id}")
	public String delete(@PathVariable Long id) {
		return eggService.delete(id);
	}

}
