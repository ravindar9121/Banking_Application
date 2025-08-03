package com.example.Banking.Application.controller;

import com.example.Banking.Application.dto.*;
import com.example.Banking.Application.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/createAccount")
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }

    @GetMapping("/balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request) {
        return userService.balanceEnquiry(request);
    }

    @GetMapping("/nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request) {
        return userService.nameEnquiry(request);
    }

    @PostMapping("/credit")
    public BankResponse creditDebit(@RequestBody CreditDebitRequest request) {
        return userService.creditDebit(request);
    }

    @PostMapping("/debit")
    public BankResponse debitRequest(@RequestBody CreditDebitRequest request) {
        return userService.debitRequest(request);
    }

    @PostMapping("/transfer")
    public BankResponse transferRequest(@RequestBody tranferRequest request) {
        return userService.transferRequest(request);
    }
}