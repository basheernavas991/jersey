package com.jazeera.jersey.security.service;

import java.util.List;

import com.jazeera.jersey.security.model.Permission;
import com.jazeera.jersey.security.model.PermissionContext;


public interface PermissionService {

	public List<Permission> getPermissions();
	public List<PermissionContext> getPermissionContexts();
}
