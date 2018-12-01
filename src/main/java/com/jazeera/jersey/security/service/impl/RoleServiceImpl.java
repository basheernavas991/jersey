package com.jazeera.jersey.security.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jazeera.jersey.security.model.Role;
import com.jazeera.jersey.security.model.RolePermission;
import com.jazeera.jersey.security.repository.RolePermissionRepository;
import com.jazeera.jersey.security.repository.RoleRepository;
import com.jazeera.jersey.security.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired RoleRepository roleRepository;
	@Autowired RolePermissionRepository rolePermissionRepository;
	
	
	@Override
	public List<Role> getRoles() {
		return (List<Role>)roleRepository.findAll();
	}

	@Override
	public Role getRole(Integer id) {
		logger.debug("Fetching User ID : {}", id);
		return roleRepository.findById(id).get();
	}

	@Override
	public Role saveRole(Role role) {
		
		logger.info("Savind Role....Role State : {}", role.toString());
		
		//saving
		Role r = roleRepository.save(role);
		
		//Assign Roles
		role.setId(r.getId());
		assignPermissions(role);
		logger.info("Save operation completed for Role : {}", role.getId());
		return getRole(r.getId());
	}

	/**
	 * Assign Permissions to Role from PermissionIds List Transient field
	 * <p> Logic: clear the current Permissions and Iterate through PermissionsIds List and build RolePermissions object and allow to persist. 
	 * @param Role
	 * @since 0.1.0
	 * @author navas
	 */
	private void assignPermissions(Role role) {
		
		logger.debug("Assigning Permissions for the Role {}", role.getId());
		logger.trace("clearing Current Permissions of the Role : {}", role.getId());
		rolePermissionRepository.deleteByRoleId(role.getId());
		
		//building RolePermissions Object and persisting
		if(role.getPermissionIds() != null && role.getPermissionIds().size() > 0){
			for(Integer id : role.getPermissionIds()){
				RolePermission rp = new RolePermission(role.getId(), id);
				rolePermissionRepository.save(rp);
			}
			logger.debug("{} Permissions assigned for Role : {}", role.getPermissionIds().size(), role.getId());
		}
	}
	
	
	@Override
	public List<Integer> getPermissionIds(Integer roleId) {
		return rolePermissionRepository.findPermissionIdsByRoleId(roleId);
	}

	
	
}
