package com.jazeera.jersey.setting.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jazeera.jersey.setting.model.PersonAddress;

@Repository
public interface PersonAddressRepository extends CrudRepository<PersonAddress, Integer> {

}
