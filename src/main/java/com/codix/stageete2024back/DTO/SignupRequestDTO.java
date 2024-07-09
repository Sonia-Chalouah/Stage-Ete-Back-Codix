package com.codix.stageete2024back.DTO;

import lombok.Data;

@Data
public class SignupRequestDTO {

    private Long Id;

    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
}
