package com.example.Banking.Application.repository;

import com.example.Banking.Application.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepo extends JpaRepository<Transaction, String> {


}
