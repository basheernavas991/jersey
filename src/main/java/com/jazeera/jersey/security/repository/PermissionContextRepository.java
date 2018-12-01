package com.jazeera.jersey.security.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jazeera.jersey.security.model.PermissionContext;

@Repository
public interface PermissionContextRepository extends CrudRepository<PermissionContext, Integer> {
	List<PermissionContext> findAllByOrderByNameAsc();
}
