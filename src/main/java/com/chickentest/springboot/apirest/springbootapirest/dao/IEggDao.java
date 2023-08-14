package com.chickentest.springboot.apirest.springbootapirest.dao;

import org.springframework.data.repository.CrudRepository;
import com.chickentest.springboot.apirest.springbootapirest.models.Egg;

public interface IEggDao extends CrudRepository<Egg, Long> {

}
