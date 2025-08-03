// /Users/ravindarreddykakunuri/Desktop/Banking-Application/src/main/java/com/example/Banking/Application/service/impl/UserServiceImpl.java
package com.example.Banking.Application.service.impl;

import com.example.Banking.Application.dto.*;
import com.example.Banking.Application.entity.User;
import com.example.Banking.Application.repository.UserRepository;
import com.example.Banking.Application.utlis.AccountUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.example.Banking.Application.utlis.AccountUtils.*;

@Service
@RequiredArgsConstructor// Best practice: Use constructor injection
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository; // Injected via constructor

    @Autowired
    EmailService emailService;

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
                .stateOfOrgin(userRequest.getStateOfOrigin()) // Typo: should be stateOfOrigin
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE") // Using an Enum for status is even better
                .accountNumber(generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .build();

        User savedUser = userRepository.save(newUser);

        EmailDetails emailsDetails = new EmailDetails();
        emailsDetails.setRecepient(savedUser.getEmail());
        emailsDetails.setSubject("ACCOUNT CREATION INFO");
        emailsDetails.setMessageBody("Congratulations Your Account Has Been Created Successfully😋/\n Your Account Details:\n Account Name:" + savedUser.getFirstName() + " " + savedUser.getLastName() + "\n Account Number:" + savedUser.getAccountNumber());
        emailService.sendEMailAlert(emailsDetails);
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

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest request) {
        // Check if account exists
        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return BankResponse.builder()
                    .responseCode(ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(ACCOUNT_NOT_EXIST_MESSAGE)
                    .build();
        }

        // Find user by account number
        User userFound = userRepository.findByAccountNumber(request.getAccountNumber());

        // Return response with account details
        return BankResponse.builder()
                .responseCode(ACCOUNT_FOUND_CODE)
                .responseMessage(ACCOUNT_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(userFound.getFirstName() + " " + userFound.getLastName())
                        .accountNumber(userFound.getAccountNumber())
                        .accountBalance(userFound.getAccountBalance())
                        .build())
                .build();
    }

    @Override
    public String nameEnquiry(EnquiryRequest request) {
        // Check if account exists
        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return ACCOUNT_NOT_EXIST_MESSAGE;
        }
        // Find user by account number
        User userFound = userRepository.findByAccountNumber(request.getAccountNumber());
        // Return response with account details
        return userFound.getFirstName() + " " + userFound.getLastName();
    }

    @Override
    public BankResponse creditDebit(CreditDebitRequest request) {
        // Check if account exists

        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return BankResponse.builder()
                    .responseCode(ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(ACCOUNT_NOT_EXIST_MESSAGE)
                    .build();

        }

        //Find the Account Number and Credit to that particular Account
        User userToCredit = userRepository.findByAccountNumber(request.getAccountNumber());

        /**
         * Logic Below
         * Fetching the Account and setAccountBalance()--> is used to update the Balance
         * userToCredit.getAccountBalance()--> Fetching the Balance from the Database(H2-Database) add(request.getAmount())--> is used to append the Amount from the User(I am using the {Postman to send the Amount to the Account Number)
         */
        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));
        userRepository.save(userToCredit);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDIT_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREDIT_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                .accountName(userToCredit.getFirstName() + " " + userToCredit.getLastName())
                        .accountNumber(request.getAccountNumber())
                        .accountBalance(userToCredit.getAccountBalance())
                        .build())
                .build();
    }

    @Override
    public BankResponse debitRequest(CreditDebitRequest request) {
        /**
         * Check whether account is Existed or not?
         *
         */
        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .build();
        }

        User userToDebit = userRepository.findByAccountNumber(request.getAccountNumber());
        int availableBalance = userToDebit.getAccountBalance().intValue();
        int debitAmount = request.getAmount().intValue();
        if(availableBalance < debitAmount)
        {
            return BankResponse.builder()
                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountName(userToDebit.getFirstName() + " " + userToDebit.getLastName())
                            .accountNumber(userToDebit.getAccountNumber())
                            .accountBalance(userToDebit.getAccountBalance())
                            .build())
                    .build();
        }
        else{
            userToDebit.setAccountBalance(userToDebit.getAccountBalance().subtract(request.getAmount()));
            userRepository.save(userToDebit);
            return BankResponse.builder()
                    .responseCode(DEBIT_SUCCESS_CODE)
                    .responseMessage(DEBIT_SUCCESS_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountName(userToDebit.getFirstName() + " " + userToDebit.getLastName())
                            .accountNumber(userToDebit.getAccountNumber())
                            .accountBalance(userToDebit.getAccountBalance())
                            .build())
                    .build();

        }


    }

    @Override
    public BankResponse transferRequest(tranferRequest request) {
        /**
         * Check Source and Destination Account Number Exist or not?
         * If Source Account Balance is lesser than transfer balance then give a message "Insufficient Balance", if not do the Balance Transfer from Source to Destination.
         * Update Both Account Balance.
         */
        boolean sourceAccExist = userRepository.existsByAccountNumber(request.getSourceAccountNumber());
        boolean destinationAccExist = userRepository.existsByAccountNumber(request.getDestinationAccountNumber());


        if (!sourceAccExist){
            if (!destinationAccExist) {
                return BankResponse.builder()
                        .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                        .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                        .accountInfo(null)
                        .build();
            }
        }
        User sourceAcc = userRepository.findByAccountNumber(request.getSourceAccountNumber());
        User destinationAcc = userRepository.findByAccountNumber(request.getDestinationAccountNumber());


        if (sourceAcc == null || destinationAcc == null) {
            // Defensive check, should not happen if existsByAccountNumber works correctly
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        if (sourceAcc.getAccountBalance().compareTo(request.getAmount()) < 0) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE) // define this constant if not defined
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountName(sourceAcc.getFirstName() + " " + sourceAcc.getLastName())
                            .accountNumber(sourceAcc.getAccountNumber())
                            .accountBalance(sourceAcc.getAccountBalance())
                            .build())
                            .build();
        }

        /**
         * Amount Deduct from Source Account
         */
        sourceAcc.setAccountBalance(sourceAcc.getAccountBalance().subtract(request.getAmount()));
        userRepository.save(sourceAcc);
        /**
         * Amount Credit to Destination Account
         */
        destinationAcc.setAccountBalance(destinationAcc.getAccountBalance().add(request.getAmount()));
        userRepository.save(destinationAcc);


        return BankResponse.builder()
                .responseCode("001")
                .responseMessage("Transfer Successful")
                .accountInfo(AccountInfo.builder()
                        .accountName(sourceAcc.getFirstName() + " " + sourceAcc.getLastName())
                        .accountNumber(sourceAcc.getAccountNumber())
                        .accountBalance(sourceAcc.getAccountBalance())
                        .build())

                .build();
    }
}



