package com.example.backend_it_hero_2.controller;


import com.example.backend_it_hero_2.entity.User;
import com.example.backend_it_hero_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experts")

public class ExpertController {
    private UserService userService;

    @Autowired
    public void UserController(UserService userService) {
        this.userService = userService;
    }

    public ExpertController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<User> addExpert(@RequestBody User expert) {
        User newExpert = userService.addExpert(expert);
        return new ResponseEntity<>(newExpert, HttpStatus.CREATED);
    }

    @GetMapping("/experts")
    public ResponseEntity<List<User>> getAllExperts() {
        List<User> experts = userService.getAllExperts();
        return new ResponseEntity<>(experts, HttpStatus.OK);
    }
    @GetMapping("/experts")
    public ResponseEntity<List<String>> getExpertParams(@RequestBody User expert){

        List<String> expertParams = userService.getExpertParams(expert);
        return new ResponseEntity<>(expertParams, HttpStatus.OK);
    }
}
