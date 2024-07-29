package com.codix.stageete2024back.DTO;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;
}
