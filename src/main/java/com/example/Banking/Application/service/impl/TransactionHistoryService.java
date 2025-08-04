package com.example.Banking.Application.service.impl;

import com.example.Banking.Application.dto.TransactionDto;
import com.example.Banking.Application.entity.Transaction;
import org.springframework.stereotype.Service;


public interface TransactionHistoryService {
    void saveTransaction(TransactionDto transactiondto);
}
