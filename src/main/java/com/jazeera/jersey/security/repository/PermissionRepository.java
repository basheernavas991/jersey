package com.jazeera.jersey.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jazeera.jersey.security.model.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Integer>{

}
