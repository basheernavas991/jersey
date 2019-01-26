package com.jazeera.jersey.security.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jazeera.jersey.security.model.PasswordReset;
import com.jazeera.jersey.security.model.User;
import com.jazeera.jersey.security.model.UserMeta;
import com.jazeera.jersey.security.model.UserRole;
import com.jazeera.jersey.security.repository.UserMetaRepository;
import com.jazeera.jersey.security.repository.UserRepository;
import com.jazeera.jersey.security.repository.UserRoleRepository;
import com.jazeera.jersey.security.service.UserService;

/**
 * Security User Service; An Implementation to <pre>org.springframework.security.core.userdetails.UserDetailsService</pre>
 * 
 * <p>How Authentication works: 
 * <p>Spring Security module execute the implementation of method loadUserByUsername() when login form is submitted.
 * 
 * @author navas
 * @since 0.1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired UserRepository userRepository;
	@Autowired UserRoleRepository userRoleRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired UserMetaRepository userMetaRepository;
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.trace("Loading user by Username : {}", username);
		
		User user = null;
		try {
			
			logger.debug("Encoded {} ", passwordEncoder.encode("top991"));
			//Fetch User
			user = getUser(username);
			
			//Validate User
			if(user == null){
				logger.warn("User not found for the username : {}", username);
				throw new UsernameNotFoundException("No such user");
			}
			
			logger.trace("Login user informations : {}", user.toString());

			if(!user.isEnabled()){
				logger.warn("User : {} is In active user", username);
				throw new UsernameNotFoundException("Inactive User");
			}
		
		} catch (EmptyResultDataAccessException e) {
			logger.warn("User not found for the username : {}", username);
			throw new UsernameNotFoundException("No such user");
		} 
		
		//Update Last Accessed Date for the User
		try {
			userRepository.updateLastAccessedDate(new Date(), user.getId());
			//TODO Write Access Audit Log
		} catch (Exception e) {
			logger.error("There is an error while processing the login request: Stack trace {}", e.getMessage());
			throw e;
		}
		
		return user;
	}
	
	
	@Override
	public void resetPassword(User user, PasswordReset passwordReset) {
		
		logger.info("Resetting password for user : {}", user.getId());
	
		logger.trace("Old Password encoded : {}", passwordEncoder.encode(passwordReset.getOldPassword()));
		logger.trace("password of user : {}", user.getPassword());
		
		//Validation: Old password should be equal to existing password
		if(!passwordEncoder.matches(passwordReset.getOldPassword(), user.getPassword())){
			logger.warn("Current password is not matching with the old password data in password reset object. aborting operation");
			throw new PasswordResetException("Existing password do not match");
		}
		
		//Validation: New Password and confirm password should be matching
		if(!passwordReset.getNewPassword().equals(passwordReset.getConfirmPassword())){
			logger.warn("New Password and confirm password do not match each other, aborting operation");
			throw new PasswordResetException("Existing password do not match");
		}
		
		//All set update the password of the user
		resetPassword(passwordReset.getNewPassword(), user.getId());
	}

	@Override
	public Boolean hasAuthority(String permission) {
		for(GrantedAuthority a : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			if(a.getAuthority().equals(permission)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void resetPassword(String password, Integer userId) {
		userRepository.updatePassword(passwordEncoder.encode(password), userId);
	}

	@Override
	public User getUser(Integer userId) {
		
		logger.debug("Fetching User ID : {}", userId);
		User user = userRepository.findById(userId).get();
		return user;
	}

	@Override
	public User getUser(String username) {
		
		logger.debug("Fetching User by Username : {}", username);
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {

		logger.debug("Fetching all Users");
		return (List<User>) userRepository.findAll();
	}


	@Override
	public User saveUser(User user) {
		
		/**
		 * Developer Note:
		 * Below Special logics are applied on User Save Operation
		 * # Password Encoding: On new User registrations password field is updated with Encoded Password
		 * # On every update of user, the user password is set from the database before calling repository save operation
		 */
		/*if(user.getPersonId() != null ){
			List<User> users=userRepository.findByPersonId(user.getPersonId());
			if(users.size() > 1){
				logger.error("Multiple user accounts not allowed for a single person. System received save operation on User. Rejecting operation...");
				throw new UserValidationException("Cannot create multiple user accounts for a single person, Please contact your administrator");
			}else if(users.size()==1){
				for(User u: users){
					if(u.getId().intValue()!=user.getId().intValue()){
						logger.error("Multiple user accounts not allowed for a single person. System received save operation on User. Rejecting operation...");
						throw new UserValidationException("Cannot create multiple user accounts for a single person, Please contact your administrator");
					}
				}
			}
			
		}*/
		if(user.getId() == null){
			//Password Encoding
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}else{
			//fetching password from the DB and setting the password to user object
			user.setPassword(userRepository.findById(user.getId()).get().getPassword());
		}
		
		logger.info("Saving User....User State : {}", user);
		
		//saving
		User u = userRepository.save(user);
		
		//Assign Roles
		user.setId(u.getId());
		/*assignRolesToUser(user);*/
		logger.info("Save operation completed for User : {}", user.getId());
		return getUser(u.getId());
	}
	
	/**
	 * Assign Roles to the User from RolesIds List Transient field
	 * <p> Logic: clear the current roles and Iterate through RolesId List and build UserRole object and allow to persist. 
	 * @param user
	 * @since 0.1.0
	 * @author navas
	 */
	private void assignRolesToUser(User user) {
		
		logger.debug("Assigning Roles for the user {}", user.getId());
		logger.trace("clearing Current Roles of the User : {}", user.getId());
		userRoleRepository.deleteByUserId(user.getId());
		
		//building UserRole Object and persisting
		if(user.getRolesIds() != null && user.getRolesIds().size() > 0){
			for(Integer id : user.getRolesIds()){
				UserRole ur = new UserRole(user.getId(), id);
				userRoleRepository.save(ur);
			}
			logger.debug("{} Roles assigned for User : {}", user.getRolesIds().size(), user.getId());
		}
	}

	@Override
	public List<Integer> getRoleIds(Integer userId) {
		return userRoleRepository.findRoleIdsByUserId(userId);
	}
	
	 @Override 
	 public DataTablesOutput<UserMeta> getUserMetas(DataTablesInput input) {
		 return userMetaRepository.findAll(input); 
	 }
	 
	
	
	
}
