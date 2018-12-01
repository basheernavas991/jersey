package com.jazeera.jersey.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jazeera.jersey.security.model.RolePermission;

@Repository
public interface RolePermissionRepository extends CrudRepository<RolePermission, Integer>{
	void deleteByRoleId(Integer roleId);
	
	@Modifying(clearAutomatically=true)
	@Query("select permissionId from RolePermission rp where rp.roleId = :id")
	public List<Integer> findPermissionIdsByRoleId(@Param("id") Integer roleId);
	
}	
