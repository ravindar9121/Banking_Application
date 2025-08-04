package com.example.Banking.Application.service.impl;

import com.example.Banking.Application.dto.TransactionDto;
import com.example.Banking.Application.entity.Transaction;
import com.example.Banking.Application.repository.TransactionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService{
    @Autowired
    TransactionHistoryRepo transactionHistoryRepo;


    @Override
    public void saveTransaction(TransactionDto transactiondto) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactiondto.getTransactionType())
                .accountNumber(transactiondto.getAccountNumber())
                .amount(transactiondto.getAmount())
                .status("ACTIVE")
                .build();
        transactionHistoryRepo.save(transaction);
        System.out.println("Transaction Saved Successfully");

    }

}
