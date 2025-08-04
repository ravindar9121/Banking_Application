package com.example.Banking.Application.controller;

import com.example.Banking.Application.dto.*;
import com.example.Banking.Application.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Banking Service API's", description = "")
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    /**
     *Swagger UI Modification Applying
    */
    @Operation(summary = "Create Account", description = "Create Account and Assigns Account Number")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")



    @PostMapping("/createAccount")
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }

    @Operation(summary = "Check Balance Enquiry", description = "Balance Can be fetched using Account Number")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")

    @GetMapping("/balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request) {
        return userService.balanceEnquiry(request);
    }

    @Operation(summary = "Name Enquiry", description = "Name Can be fetched using Account Admin Name")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")

    @GetMapping("/nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request) {
        return userService.nameEnquiry(request);
    }


    @Operation(summary = "Credit Request", description = "Request Credit from the Bank")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")

    @PostMapping("/credit")
    public BankResponse creditDebit(@RequestBody CreditDebitRequest request) {
        return userService.creditDebit(request);
    }


    @Operation(summary = "Debit Request", description = "Debit Request from the Bank")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")


    @PostMapping("/debit")
    public BankResponse debitRequest(@RequestBody CreditDebitRequest request) {
        return userService.debitRequest(request);
    }


    @Operation(summary = "Funds Transfer", description = "Funds Transfer from Account to Account with Mailing Service")
    @ApiResponse(responseCode = "200", description = "Successful Operation")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")

    @PostMapping("/transfer")
    public BankResponse transferRequest(@RequestBody TranferRequest request) {
        return userService.transferRequest(request);
    }
}