package com.jazeera.jersey.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jazeera.jersey.security.model.PasswordReset;
import com.jazeera.jersey.security.model.User;


public interface UserService extends UserDetailsService{

	public User getUser(Integer userId);
	public User getUser(String username);
	public List<User> getUsers();
	public User saveUser(User user);
	public void resetPassword(User user, PasswordReset passwordReset);
	public void resetPassword(String password, Integer userId);
	
	//### User Roles
	public List<Integer> getRoleIds(Integer userId);

	/**
	 * Check already login user is authorized for the permission
	 * @param permission
	 * @return True or False
	 * @author saneesh
	 */
	public Boolean hasAuthority(String permission);

}
