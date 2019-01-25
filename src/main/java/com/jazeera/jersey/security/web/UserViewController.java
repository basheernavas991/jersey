package com.jazeera.jersey.security.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazeera.jersey.security.model.User;
import com.jazeera.jersey.security.model.UserMeta;
import com.jazeera.jersey.security.service.RoleService;
import com.jazeera.jersey.security.service.UserService;
import com.jazeera.jersey.security.service.impl.UserValidationException;
import com.jazeera.jersey.security.service.impl.UserValidator;
import com.jazeera.jersey.setting.model.ViewUtil;
import com.jazeera.jersey.setting.service.SettingsService;


/**
 * User Controller
 * 
 * Any request related to User data will hit this view layer. 
 * @author navas
 * @since 0.1.0
 */
@RequestMapping("/user")
@Controller
public class UserViewController {

	
	private static final Logger logger = LoggerFactory.getLogger(UserViewController.class);

	@Autowired UserService userService;
	@Autowired RoleService roleService;
	//@Autowired PersonService personService;
	//@Autowired LookupService lookupService;
	@Autowired SettingsService settingsService;
	//@Autowired SystemConfigurationRepository systemConfigurationRepository;

	/**
	 * View User Manager 
	 * @author navas
	 */
	//@PreAuthorize("hasAuthority('SECU_USER_VIEW')")
	@RequestMapping("/manager")
	public String viewUserManager(Model model){

		logger.trace("Routing to User Manager");
		model.addAttribute("user", new User());
		initUserView(model);
		return "security/user-manager";
	}
	
	
	
	/**
	 * Initialize User View 
	 * @param model
	 * @return {@link Model}
	 * @author navas
	 */
	private Model initUserView(Model model){
		model.addAttribute("availableRoles", roleService.getRoles());
		return model;
	}
	
	
	/**
	 * View User
	 * <p> Used to view a Empty User Account or Existing User Account (based on the id)
	 * @param model
	 * @param userId
	 * @return
	 */
	//@PreAuthorize("hasAuthority('SECU_USER_VIEW')")
	@RequestMapping(value="/view")
	public String viewUser(
			Model model,
			@RequestParam(value="id", required=false) Integer userId){
		
		User user = new User();
		if(userId != null && userId > 0){
			user = userService.getUser(userId);
			user.setCreatedUser(userService.getUser(user.getCreatedBy()));
			user.setLastModifiedUser(userService.getUser(user.getLastModifiedBy()));
			user.setRolesIds(userService.getRoleIds(userId));
		}else{
			user = new User();
			user.setEnabled(true);
		}
		initUserView(model);
		model.addAttribute("user", user);
		return "security/user-manager";
		
	}
	/**
	 * Save User
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	//@PreAuthorize("hasAuthority('SECU_USER_SAVE')")
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute User user,
			BindingResult result,
			Model model){
		
		//Calling Custom Validator
		UserValidator validator = new UserValidator();
		validator.validate(user, result);
		
		ViewUtil viewUtil = new ViewUtil();
		logger.debug("The state of User: {}", user.toString());
		if(result.hasErrors()){
			logger.debug("Validation Errors Present. Count : {},errors : {} Sending the Validation Messages to Client", result.getErrorCount(), result.getAllErrors().toString());
			viewUtil.addError(model, "global.validation.error");
			initUserView(model);
			return "security/user-manager";
		}else{
			
			try {
				logger.info("User Object recieved for Save Operation, Calling Service Layer for persisting....");
				User u = userService.saveUser(user);
				viewUtil.addSuccess(model, "global.save.success");
				return viewUser(model, u.getId());
			} catch (DataIntegrityViolationException e) {
				logger.warn("Data Integration Violation occured. Message : {}", e.getMessage());
				viewUtil.addWarning(model, "user.username.unique.error");
				initUserView(model);
				return "security/user-manager";
			} catch (UserValidationException e) {
				logger.warn("User Validation Exception occured. Message : {}", e.getMessage());
				viewUtil.addWarning(model, "user.person.unique.error");
				initUserView(model);
				return "security/user-manager";
			}
		}	
	}
	/**
	 * Reset Password User
	 * @param userId
	 * @param model
	 * @return
	 */
	//@PreAuthorize("hasAuthority('SECU_USER_PASS_RESET')")
	@RequestMapping(value="/reset/password", method = RequestMethod.POST)
	public String resetPasword(Model model,
			@RequestParam(value="userId", required=true) Integer userId,
			@RequestParam(value="newPassword", required=true) String password){
		
		ViewUtil viewUtil = new ViewUtil();
		logger.info("updating user : {} password to : {}", userId, password);
		userService.resetPassword(password, userId);
		logger.info("Password updated successfully");
		viewUtil.addSuccess(model, "user.password.reset.success");
		return viewUser(model, userId);
	}
	
	/**
	 * Get All Users
	 * @return {@link List} {@link User}
	 * @author Saneesh.Jose
	 */
	//@PreAuthorize("hasAuthority('SECU_USER_VIEW')")
	
	 @JsonView(DataTablesOutput.View.class)
	 @RequestMapping(value="/json", method = RequestMethod.GET)
	 public @ResponseBody DataTablesOutput<UserMeta> getUsers(DataTablesInput input){ 
		 return userService.getUserMetas(input); 
	 }
	 
	
}
