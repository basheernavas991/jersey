package com.jazeera.jersey.security.service;

import java.util.List;

import com.jazeera.jersey.security.model.Role;


public interface RoleService {

	public List<Role> getRoles();
	public Role getRole(Integer id);
	public Role saveRole(Role role);

	//### Role Permission
	public List<Integer> getPermissionIds(Integer roleId);
}
