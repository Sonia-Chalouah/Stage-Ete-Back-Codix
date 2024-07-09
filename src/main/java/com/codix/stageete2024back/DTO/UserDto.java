package com.codix.stageete2024back.DTO;

import com.codix.stageete2024back.Entity.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long Id;

    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private UserRole role;
}
