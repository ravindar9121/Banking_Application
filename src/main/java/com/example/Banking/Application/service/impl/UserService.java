package com.example.Banking.Application.service.impl;

import com.example.Banking.Application.dto.BankResponse;
import com.example.Banking.Application.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
}
