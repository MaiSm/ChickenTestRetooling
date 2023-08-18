package com.chickentest.springboot.apirest.springbootapirest.dao;

import org.springframework.data.repository.CrudRepository;
import com.chickentest.springboot.apirest.springbootapirest.models.Chicken;

public interface IChickenDao extends CrudRepository<Chicken, Long>{

	int countByFarmId(Long id);
}
