package com.jazeera.jersey.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jazeera.jersey.security.model.Permission;
import com.jazeera.jersey.security.model.PermissionContext;
import com.jazeera.jersey.security.repository.PermissionContextRepository;
import com.jazeera.jersey.security.repository.PermissionRepository;
import com.jazeera.jersey.security.service.PermissionService;


@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired PermissionRepository permissionRepository;
	@Autowired PermissionContextRepository permissionContextRepository;
	
	public List<Permission> getPermissions() {
		return (List<Permission>) permissionRepository.findAll();
	}

	public List<PermissionContext> getPermissionContexts() {
		return permissionContextRepository.findAllByOrderByNameAsc();
	}
}
