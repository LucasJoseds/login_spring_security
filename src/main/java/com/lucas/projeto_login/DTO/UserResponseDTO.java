package com.lucas.projeto_login.DTO;

import java.util.stream.Collectors;

import com.lucas.projeto_login.Model.Role;
import com.lucas.projeto_login.Model.User;



public record UserResponseDTO(Long id ,String name, 
                              String email, String roles) {


    public static UserResponseDTO valueOf(User user){

        String roles = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));

        return new UserResponseDTO(user.getId(),
        user.getName(), 
        user.getEmail(),
        roles);
    }

    public static User updateUser(UserResponseDTO dto, User user ){

        user.setName(dto.name());
        user.setEmail(dto.email());

        return user;
    }

   

}
