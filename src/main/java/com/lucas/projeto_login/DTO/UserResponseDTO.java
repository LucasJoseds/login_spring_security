package com.lucas.projeto_login.DTO;

import com.lucas.projeto_login.Model.User;



public record UserResponseDTO(String name, 
                              String email) {


    public static UserResponseDTO valueOf(User user){
        return new UserResponseDTO(user.getName(), user.getEmail());
    }

}
