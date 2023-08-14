package com.chickentest.springboot.apirest.springbootapirest.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.chickentest.springboot.apirest.springbootapirest.models.entities.Egg;

public interface IEggDao extends CrudRepository<Egg, Long> {

}
