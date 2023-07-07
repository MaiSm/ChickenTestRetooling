package com.chickentest.springboot.apirest.springbootapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.chickentest.springboot.apirest.springbootapirest.models.entities.Farm;

public interface IFarmDao extends CrudRepository<Farm, Long> {

}
