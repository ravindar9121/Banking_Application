package com.example.Banking.Application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String recepient;
    private String messageBody;
    private String subject;
    private String attachments;
}