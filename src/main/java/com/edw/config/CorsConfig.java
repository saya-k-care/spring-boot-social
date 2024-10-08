package com.edw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    //@Value("${allowed.origins}")
    private String[] theAllowedOrigins = {"*"};

    //@Value("${spring.data.rest.base-path}")
    private String basePath = "/api";

    @Override
    public void addCorsMappings(CorsRegistry cors) {
    	System.out.println("adding logging...");
        cors.addMapping(basePath + "/**").allowedOrigins(theAllowedOrigins);
    }
}