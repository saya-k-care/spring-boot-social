package com.edw.config;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mgbt.socialapp_backend.model.entity.UserApp;
import com.mgbt.socialapp_backend.model.service.UserService;


@RestController
@RequestMapping(value = "/api/foos")
public class FooController {
    @Autowired
    private UserService userService;

    @Autowired
    MessageSource messageSource;
    
   // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{id}")
    public String findOne(@PathVariable Long id) {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("user-->" + obj);
        return "hello";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserApp user, Locale locale) {
    	System.out.println("login");
        UserApp userFound = userService.findByUsername(user.getUsername());
        Map<String, Object> response = new HashMap<>();
        System.out.println("yserfound-->" + userFound.getName());
        if (userFound != null) {
            userFound = userService.checkIfUserIsPersisted(userFound, user); //Needed if the user updates their first and last name from Keycloak
            //response.put("message", messageSource.getMessage("appController.login.userFound", null, locale));
            response.put("status", HttpStatus.OK.value());
            response.put("user", userFound);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            userService.save(user);
            //response.put("message", messageSource.getMessage("appController.login.userCreated", null, locale));
            response.put("status", HttpStatus.CREATED.value());
            response.put("user", user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }
 
}