package com.chickentest.springboot.apirest.springbootapirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chickentest.springboot.apirest.springbootapirest.models.Chicken;
import com.chickentest.springboot.apirest.springbootapirest.services.IChickenService;

@CrossOrigin(origins= {"http://localhost:4200"}) 
@RestController
public class ChickenRestController {
	
	@Autowired
	IChickenService chickenService;
		
	@GetMapping("/chickens")
	public ResponseEntity<?> findAll(){
		Map<String, Object> response = new HashMap<>();
		List<Chicken> chickenList;
		try {
			chickenList = chickenService.findAll();
			if(chickenList.size() == Chicken.getZero()) {
				response.put("Message", "The are no Chickens in the Database.");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<List<Chicken>>(chickenList,HttpStatus.OK);
			}
		}catch(DataAccessException e) {
			response.put("Error", e.getMessage()+ " : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
