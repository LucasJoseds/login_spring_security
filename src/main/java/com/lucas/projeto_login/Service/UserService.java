package com.lucas.projeto_login.Service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.projeto_login.DTO.UserDTO;
import com.lucas.projeto_login.DTO.UserResponseDTO;
import com.lucas.projeto_login.Model.User;
import com.lucas.projeto_login.Repository.RoleRepository;
import com.lucas.projeto_login.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO create(UserDTO dto) {

        var roleUser = roleRepository.findByName("USER");

        User nUser = new User();
        nUser.setName(dto.name());
        nUser.setEmail(dto.email());
        nUser.setPassword(passwordEncoder.encode(dto.password()));
        nUser.getRoles().add(roleUser);
        userRepository.save(nUser);

        var userCreated = UserResponseDTO.valueOf(nUser);
        
        return userCreated;

    }

    public UserResponseDTO updateUser(Long id, UserResponseDTO dto) throws Exception {

        var oldUser = userRepository.findById(id).get();

        var nUser = UserResponseDTO.updateUser(dto, oldUser);
        userRepository.save(nUser);
        var userP = UserResponseDTO.valueOf(nUser);
        return userP;

    }

    public List<UserResponseDTO> listName(int paginas, int itens) {

        return userRepository.findAll(PageRequest.of(paginas, itens)).getContent().stream()
                .map(e -> UserResponseDTO.valueOf(e)).toList();
    }
}
