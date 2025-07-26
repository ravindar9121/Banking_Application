package com.example.Banking.Application.controller;

import com.example.Banking.Application.dto.BankResponse;
import com.example.Banking.Application.dto.UserRequest;
import com.example.Banking.Application.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/createAccount")
    public BankResponse createAccount(@RequestBody  UserRequest userRequest){
        return userService.createAccount(userRequest);
    }

    @RequestMapping("/greeting")
    public String gr(){
        return "WElcome";
    }
}