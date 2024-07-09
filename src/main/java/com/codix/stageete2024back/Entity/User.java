package com.codix.stageete2024back.Entity;

import com.codix.stageete2024back.DTO.UserDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private UserRole role;

    public UserDto getDto(){
        UserDto userDto = new UserDto();
        userDto.setId(Id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setRole(role);

        return userDto;
    }




}
