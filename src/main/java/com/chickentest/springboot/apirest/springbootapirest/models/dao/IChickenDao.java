package com.chickentest.springboot.apirest.springbootapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.chickentest.springboot.apirest.springbootapirest.models.entities.Chicken;

public interface IChickenDao extends CrudRepository<Chicken, Long>{

}
