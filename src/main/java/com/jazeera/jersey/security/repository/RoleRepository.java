package com.jazeera.jersey.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jazeera.jersey.security.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
