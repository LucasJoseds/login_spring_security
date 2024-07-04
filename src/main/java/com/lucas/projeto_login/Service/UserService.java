package com.lucas.projeto_login.Service;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lucas.projeto_login.DTO.UserDTO;
import com.lucas.projeto_login.DTO.UserResponseDTO;
import com.lucas.projeto_login.Model.User;
import com.lucas.projeto_login.Repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
       this.userRepository = userRepository;
    }


    public User create (UserDTO dto){

        User nUser =  new User();
        nUser.setName(dto.name());
        nUser.setPassword(dto.password());
        nUser.setEmail(dto.email());
        
        return userRepository.save(nUser);

    }

    //public List<User> listUserPages(int paginas , int itens){
        
       // var listUser = userRepository.findAll(PageRequest.of(paginas,itens)).getContent();    
       // return listUser;
   // }

    public List<UserResponseDTO> listName(int paginas , int itens){

        return userRepository.findAll(PageRequest.of(paginas, itens)).getContent().stream()
        .map(e -> UserResponseDTO.valueOf(e)).toList();
    }
}
