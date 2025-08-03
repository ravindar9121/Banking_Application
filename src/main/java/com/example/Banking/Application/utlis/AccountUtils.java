package com.example.Banking.Application.utlis;

import java.time.Year;



public class AccountUtils {
    public static final String ACCOUNT_EXIST_MESSAGE  = "User Exist";
    public static final String ACCOUNT_EXIST_CODE  = "001";
    public static final String ACCOUNT_CREATION_SUCCESS  = "ACCOUNT CREATION SUCCESSFUL";
    public static final String ACCOUNT_CREATION_MESSAGE = "ACCOUNT CREATED";
    public static final String ACCOUNT_NOT_EXIST_CODE = "002";
    public static final String ACCOUNT_NOT_EXIST_MESSAGE = "ACCOUNT DOES NOT EXIST";
    public static final String ACCOUNT_FOUND_CODE = "003";
    public static final String ACCOUNT_FOUND_MESSAGE = "ACCOUNT FOUND";
    public static final String ACCOUNT_CREDIT_SUCCESS = "100";
    public static final String ACCOUNT_CREDIT_SUCCESS_MESSAGE = "AMOUNT CREDIT SUCCESSFUL";
    public static final String INSUFFICIENT_BALANCE_CODE = "000";
    public static final String INSUFFICIENT_BALANCE_MESSAGE = "INSUFFICIENT BALANCE";
    public static final String DEBIT_SUCCESS_CODE = "001";
    public static final String DEBIT_SUCCESS_MESSAGE = "The Required Amount has been Debited Successfully";



    public static String generateAccountNumber(){
        /**
         * Creating the Account Number like Current_Year + Random_Number(6 Digits in my case)
         */
        Year current_year = Year.now();
        int min = 100000;
        int max = 999999;
        int randNumber = (int) Math.floor(Math.random() * (max - min +1));
        String randomNumber = String.valueOf(randNumber);
        String year = String.valueOf(current_year);
        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(year).append(randomNumber);
        return accountNumber.toString();


    }



}
