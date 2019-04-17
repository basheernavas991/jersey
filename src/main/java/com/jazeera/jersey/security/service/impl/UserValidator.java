package com.jazeera.jersey.security.service.impl;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jazeera.jersey.security.model.User;


public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return clazz.equals(User.class);
	}

	public void validate(Object target, Errors errors) {
		
		User user = (User) target;
		
		
		/**
		 * Password Validation
		 * Check only on initial create mode
		 * password cannot be null
		 * confirmPassword cannot be null
		 * password should be equal to confirmPassword
		 * Password should have one special character and number character should be greater than 5
		 */
		if(user.getId() == null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "user.confirmPassword.required");
			
			if(user.getPassword() != null && !user.getPassword().isEmpty() &&
					user.getConfirmPassword() != null && !user.getConfirmPassword().isEmpty() &&
					!user.getPassword().equals(user.getConfirmPassword())){
				errors.rejectValue("password", "user.password.mismatch");
			}
		}
	}
}
