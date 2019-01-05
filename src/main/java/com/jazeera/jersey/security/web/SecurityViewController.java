package com.jazeera.jersey.security.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jazeera.jersey.setting.service.SettingsService;

@Controller
@RequestMapping("/security")
public class SecurityViewController {

	@Autowired SettingsService settingService;
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	/**
	 * Login 
	 * @return
	 * @author saneesh
	 */
	@RequestMapping("/login")
	String login(Model model){
		logger.trace("Routing to Login Screen");
		//model.addAttribute("version", settingService.getVersion());
	    return "security/login-new";
	}
}
