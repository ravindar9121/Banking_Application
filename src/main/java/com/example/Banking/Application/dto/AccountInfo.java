package com.example.Banking.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountInfo {
    private String accountName;
    private String accountNumber;
    private BigDecimal accountBalance;

}
