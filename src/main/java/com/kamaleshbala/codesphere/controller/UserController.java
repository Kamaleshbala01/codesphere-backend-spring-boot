package com.kamaleshbala.codesphere.controller;

import com.kamaleshbala.codesphere.model.UserModel;
import com.kamaleshbala.codesphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String greet(){
        return "greet";
    }

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody UserModel user){
        return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel user){
        System.out.println("data");
        return new ResponseEntity<>(userService.login(user),HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserModel>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }
}
