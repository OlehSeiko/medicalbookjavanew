package com.example.medicalbookjavanew.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class MainController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
