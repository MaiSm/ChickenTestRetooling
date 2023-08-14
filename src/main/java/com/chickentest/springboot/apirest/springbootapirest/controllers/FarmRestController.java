package com.chickentest.springboot.apirest.springbootapirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.chickentest.springboot.apirest.springbootapirest.models.Farm;
import com.chickentest.springboot.apirest.springbootapirest.services.IFarmService;

@RestController
public class FarmRestController {

	@Autowired
	IFarmService farmService;
	
	@GetMapping("/farms")
	public ResponseEntity<?> findAll() {
		Map<String, Object> response = new HashMap<>();
		List<Farm> farmList = null;
		try {
			farmList = farmService.findAll();
			if(farmList == null) {
				response.put("Message", "The are no Farms in the Database.");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<List<Farm>>(farmList,HttpStatus.OK);
			}
		} catch (DataAccessException e){
			response.put("Error", e.getMessage()+ " : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/farms/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Farm farm = null;
		try {
			farm = farmService.findById(id);
			if(farm == null) {
				response.put("Message", "The Farm Id: " + id + " doesn't exist in the Database.");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Farm>(farm,HttpStatus.OK);
			}
			
		}catch(DataAccessException e){
			response.put("Error", e.getMessage()+ " : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/farms/{id}")
	public ResponseEntity<?> update(@RequestBody Farm farm, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			farmService.update(id, farm);
			response.put("Message", "The farm Id: " + id + " has been updated.");
			return new ResponseEntity<Farm>(farm,HttpStatus.OK);
		}catch(Exception e) {
			response.put("Error", e.getMessage()+ " : " + e.getCause());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@PutMapping("/farms/time/{days}")
	public ResponseEntity<?> addDays(@PathVariable int days) {
		Map<String, Object> response = new HashMap<>();
		try {
			if(days < 1 || days > 5) {
				response.put("Message", "You can only select a number of days between 1 and 5");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_ACCEPTABLE);
			}else {
				farmService.addDays(days);
				response.put("Message", "You have moved " + days + " days in time.");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
			}
		}catch(Exception e){
			response.put("Error", e.getMessage()+ " : " + e.getCause());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}		
	}
}
