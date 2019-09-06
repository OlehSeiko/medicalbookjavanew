package com.example.medicalbookjavanew.controllers;

import com.example.medicalbookjavanew.dao.UserDao;
import com.example.medicalbookjavanew.entity.User;
import com.example.medicalbookjavanew.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/save")
    public void save(@RequestBody User user) {
        System.out.println(user);
        user.setMonth(passwordEncoder.encode(user.getMonth()));
        userService.save(user);
    }
}
