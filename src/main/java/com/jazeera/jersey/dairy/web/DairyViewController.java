package com.jazeera.jersey.dairy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DairyViewController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(DairyViewController.class);
	
	@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
	
	@GetMapping("/")
    public String home(Model model) {
        return "dashboard/home";
    }
	

}
