package com.lucas.projeto_login.DTO;

import com.lucas.projeto_login.Model.User;



public record UserResponseDTO(Long id ,String name, 
                              String email) {


    public static UserResponseDTO valueOf(User user){
        return new UserResponseDTO(user.getId(),user.getName(), user.getEmail());
    }

   

}
