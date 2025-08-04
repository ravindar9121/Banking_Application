package com.example.Banking.Application.service.impl;

import com.example.Banking.Application.dto.*;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquiryRequest request);
    String nameEnquiry(EnquiryRequest request);
    BankResponse creditDebit(CreditDebitRequest request);
    BankResponse debitRequest(CreditDebitRequest request);
    BankResponse transferRequest(TranferRequest request);
}
