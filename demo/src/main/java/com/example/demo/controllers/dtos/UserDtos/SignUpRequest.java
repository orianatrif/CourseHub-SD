package com.example.demo.controllers.dtos.UserDtos;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;

}
