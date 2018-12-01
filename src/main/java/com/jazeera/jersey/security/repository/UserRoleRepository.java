package com.jazeera.jersey.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jazeera.jersey.security.model.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer>{

	public Long deleteByUserId(Integer userId);
	
	@Modifying(clearAutomatically=true)
	@Query("select roleId from UserRole ur where ur.userId = :id")
	public List<Integer> findRoleIdsByUserId(@Param("id") Integer userId);
	
	
}	
