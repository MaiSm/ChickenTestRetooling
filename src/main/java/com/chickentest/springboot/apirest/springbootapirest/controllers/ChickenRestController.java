package com.chickentest.springboot.apirest.springbootapirest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.chickentest.springboot.apirest.springbootapirest.models.Chicken;
import com.chickentest.springboot.apirest.springbootapirest.services.IChickenService;

@CrossOrigin(origins= {"http://localhost:4200"}) 
@RestController
public class ChickenRestController {
	
	@Autowired
	IChickenService chickenService;
		
	@GetMapping("/chickens")
	public List<Chicken> findAll(){
		return chickenService.findAll();
	}
	
	@GetMapping("/chickens/{id}")
	public Chicken findById(@PathVariable Long id) {
		return chickenService.findById(id);
	}
	
	@PostMapping("/chickens")
	public Chicken create(@RequestBody Chicken chicken) {
		return chickenService.save(chicken);
	}
	
	@PutMapping("/chickens/{id}")
	public Chicken update(@PathVariable Long id, @RequestBody Chicken chicken) {
		return chickenService.update(id, chicken);		
	}
	
	@DeleteMapping("/chickens/{id}")
	public String delete(@PathVariable Long id) {
		return chickenService.delete(id);
	}	
}
