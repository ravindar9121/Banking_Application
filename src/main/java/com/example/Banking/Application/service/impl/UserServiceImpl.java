// /Users/ravindarreddykakunuri/Desktop/Banking-Application/src/main/java/com/example/Banking/Application/service/impl/UserServiceImpl.java
package com.example.Banking.Application.service.impl;

import com.example.Banking.Application.dto.AccountInfo;
import com.example.Banking.Application.dto.BankResponse;
import com.example.Banking.Application.dto.UserRequest;
import com.example.Banking.Application.entity.User;
import com.example.Banking.Application.repository.UserRepository;
import com.example.Banking.Application.utlis.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.example.Banking.Application.utlis.AccountUtils.*;

@Service
@RequiredArgsConstructor // Best practice: Use constructor injection
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // Injected via constructor

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        // Check if the user already has an account
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            // If the user exists, we should return their info, not an empty object.
            // Note: This part could be enhanced by fetching the user and returning their actual details.
            return BankResponse.builder()
                    .responseCode(ACCOUNT_EXIST_CODE)
                    .responseMessage(ACCOUNT_EXIST_MESSAGE)
                    .build();
        }

        // Create the new user entity
        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrgin(userRequest.getStateOfOrgin()) // Typo: should be stateOfOrigin
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE") // Using an Enum for status is even better
                .accountNumber(generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .build();

        User savedUser = userRepository.save(newUser);

        // Return a complete and successful response
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS) // Assuming you add this constant
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE) // Assuming you add this constant
                .accountInfo(AccountInfo.builder()
                        .accountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountBalance(savedUser.getAccountBalance())
                        .build())
                .build();
    }
}