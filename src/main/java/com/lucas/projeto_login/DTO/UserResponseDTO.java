package com.lucas.projeto_login.DTO;

import java.util.Set;

import com.lucas.projeto_login.Model.Role;
import com.lucas.projeto_login.Model.User;



public record UserResponseDTO(Long id ,String name, 
                              String email, Set<Role> roles) {


    public static UserResponseDTO valueOf(User user){


        return new UserResponseDTO(user.getId(),
        user.getName(), 
        user.getEmail(),
        user.getRoles());
    }

    public static User updateUser(UserResponseDTO dto, User user ){

        user.setName(dto.name());
        user.setEmail(dto.email());

        return user;
    }

   

}
