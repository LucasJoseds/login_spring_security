package com.lucas.projeto_login.DTO;

import com.lucas.projeto_login.Model.User;

public record UserDTO(String name, 
                      String email, 
                      String password,
                      int idade) {


                         public static User converterDTO(UserDTO dto){

                            User user = new User();
                            user.setEmail(dto.email);
                            user.setName(dto.name);
                            user.setPassword(dto.password());
                            user.setIdade(dto.idade());

                            return user;
                         }

}
