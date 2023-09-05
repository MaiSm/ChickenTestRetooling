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
import com.chickentest.springboot.apirest.springbootapirest.models.Egg;
import com.chickentest.springboot.apirest.springbootapirest.services.IEggService;

@CrossOrigin(origins= {"http://localhost:4200"}) 
@RestController
public class EggRestController {
	
	@Autowired
	IEggService eggService;
	
	@GetMapping("/eggs")
	public ResponseEntity<?> findAll() {
		Map<String, Object> response = new HashMap<>();
		List<Egg> eggList;
		try{
			eggList = eggService.findAll();	
			if(eggList.size() == Egg.getZero()) {
				response.put("Message", "The are no Eggs in the Database.");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<List<Egg>>(eggList,HttpStatus.OK);
			}
		}catch(DataAccessException e) {
			response.put("Error", e.getMessage()+ " : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}	
}
