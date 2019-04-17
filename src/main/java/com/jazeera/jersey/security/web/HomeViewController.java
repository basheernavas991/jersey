package com.jazeera.jersey.security.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jazeera.jersey.setting.service.SettingsService;

@Controller
public class HomeViewController {

	@Autowired SettingsService settingService;
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@RequestMapping("/test/home")
    public String home(Model model){
        return "theme/layout";
    }
}
