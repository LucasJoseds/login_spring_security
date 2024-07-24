package com.lucas.projeto_login.Service;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lucas.projeto_login.DTO.UserDTO;
import com.lucas.projeto_login.DTO.UserResponseDTO;
import com.lucas.projeto_login.Model.Role;
import com.lucas.projeto_login.Model.User;
import com.lucas.projeto_login.Repository.RoleRepository;
import com.lucas.projeto_login.Repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
       this.userRepository = userRepository;
       this.roleRepository = roleRepository;
    }


    public User create (UserDTO dto){


        Role r = new Role();
        r.setName("User");
        roleRepository.save(r);

        User nUser = new User();
        nUser.setName(dto.name());
        nUser.setEmail(dto.email());
        nUser.setPassword(dto.password());
        nUser.getRoles().add(r);
        return userRepository.save(nUser);

    }

   public UserResponseDTO updateUser(Long id, UserResponseDTO dto){

    var user = userRepository.findById(id).get();

    if(user==null){
        return null;
    }
    user.setEmail(dto.email());
    user.setName(dto.name());

    var nUser = UserResponseDTO.valueOf(user);

    userRepository.save(user);

    return nUser;

  
   }

   

    public List<UserResponseDTO> listName(int paginas , int itens){

        return userRepository.findAll(PageRequest.of(paginas, itens)).getContent().stream()
        .map(e -> UserResponseDTO.valueOf(e)).toList();
    }
}
