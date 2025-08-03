package com.example.Banking.Application.service.impl;

import com.example.Banking.Application.dto.BankResponse;
import com.example.Banking.Application.dto.CreditDebitRequest;
import com.example.Banking.Application.dto.EnquiryRequest;
import com.example.Banking.Application.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquiryRequest request);
    String nameEnquiry(EnquiryRequest request);
    BankResponse creditDebit(CreditDebitRequest request);
    BankResponse debitRequest(CreditDebitRequest request);
}
