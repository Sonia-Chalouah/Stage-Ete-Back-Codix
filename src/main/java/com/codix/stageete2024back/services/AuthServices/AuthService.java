package com.codix.stageete2024back.services.AuthServices;

import com.codix.stageete2024back.DTO.SignupRequestDTO;
import com.codix.stageete2024back.DTO.UserDto;

public interface AuthService {
    UserDto signupClient (SignupRequestDTO signupRequestDTO);
    UserDto signupCompany (SignupRequestDTO signupRequestDTO);
    Boolean presentByEmail(String email);

}
