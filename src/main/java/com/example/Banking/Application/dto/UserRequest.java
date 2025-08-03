package com.example.Banking.Application.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String otherName;
    private String gender;
    private String address;
    private String stateOfOrigin;
    private String email;
    private String phoneNumber;
    private String alternativePhoneNumber;

}
