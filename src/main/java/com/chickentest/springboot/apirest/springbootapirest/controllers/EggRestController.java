package com.chickentest.springboot.apirest.springbootapirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.chickentest.springboot.apirest.springbootapirest.models.Egg;
import com.chickentest.springboot.apirest.springbootapirest.services.IEggService;

@CrossOrigin(origins= {"http://localhost:4200"}) 
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
	
	@GetMapping("/eggs/farms/{id}")
	public ResponseEntity<?> countEggs(@PathVariable long id){
		Map<String, Object> response = new HashMap<>();
		try {
			int numberOfChickens = eggService.countEggs(id);
			return new ResponseEntity<Integer>(numberOfChickens,HttpStatus.OK);			
		}catch(Exception e) {
			response.put("Error", e.getMessage()+ " : " + e.getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
