package com.example.Banking.Application.utlis;

import java.time.Year;



public class AccountUtils {
    public static final String ACCOUNT_EXIST_MESSAGE  = "User Exist";
    public static final String ACCOUNT_EXIST_CODE  = "001";
    public static final String ACCOUNT_CREATION_SUCCESS  = "ACCOUNT CREATION SUCCESSFUL";
    public static final String ACCOUNT_CREATION_MESSAGE = "ACCOUNT CREATED";


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
