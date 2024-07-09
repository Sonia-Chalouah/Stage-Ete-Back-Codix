package com.codix.stageete2024back.services.AuthServices;

import com.codix.stageete2024back.DTO.SignupRequestDTO;
import com.codix.stageete2024back.DTO.UserDto;
import com.codix.stageete2024back.Entity.User;
import com.codix.stageete2024back.Entity.UserRole;
import com.codix.stageete2024back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient (SignupRequestDTO signupRequestDTO){
        User user = new User();

        user.setName(signupRequestDTO.getName());
        user.setLastname(signupRequestDTO.getLastname());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword((signupRequestDTO.getPassword()));
        user.setRole(UserRole.CLIENT);

        return userRepository.save(user).getDto();
    }

    public Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }

    public UserDto signupCompany (SignupRequestDTO signupRequestDTO){
        User user = new User();

        user.setName(signupRequestDTO.getName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword((signupRequestDTO.getPassword()));
        user.setRole(UserRole.COMPANY);

        return userRepository.save(user).getDto();
    }
}
