package com.chickentest.springboot.apirest.springbootapirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.chickentest.springboot.apirest.springbootapirest.models.Farm;

public interface IFarmDao extends CrudRepository<Farm, Long> {

}
