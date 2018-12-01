package com.jazeera.jersey.security.service.impl;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jazeera.jersey.security.model.PasswordReset;

/**
 * Custom Validator for Password Reset.
 * @author navas
 *
 */
public class PasswordResetValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(PasswordReset.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PasswordReset p = (PasswordReset) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "passwordReset.oldPassword.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "passwordReset.newPassword.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "passwordReset.confirmPassword.required");
		
		if(p.getNewPassword() != null && !p.getNewPassword().isEmpty() &&
				p.getConfirmPassword() != null && !p.getConfirmPassword().isEmpty() &&
				!p.getNewPassword().equals(p.getConfirmPassword())){
			errors.rejectValue("newPassword", "passwordReset.newPassword.mismatch");
		}
	}
}
