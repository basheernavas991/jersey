package com.jazeera.jersey.setting.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jazeera.jersey.setting.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>{
	
}
