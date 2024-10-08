package com.mgbt.socialapp_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class HelloController {

    @GetMapping("/get")
    public String hello() {
       return "Hello";
    }
    record Message(String message){};
}
