package com.jazeera.jersey;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("/theme/layout");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/greeting").setViewName("greeting");
        registry.addViewController("/login").setViewName("login");
    }

}
