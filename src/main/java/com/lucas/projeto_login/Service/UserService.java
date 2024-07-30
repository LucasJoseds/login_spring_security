package com.lucas.projeto_login.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.lucas.projeto_login.DTO.AlterPasswordDTO;
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

    Logger logger = LoggerFactory.getLogger(UserService.class);

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

    public UserResponseDTO updateUserlogged(UserResponseDTO dto, JwtAuthenticationToken token) throws Exception {

        var oldUser = userRepository.findById(Long.parseLong(token.getName())).get();
        if (oldUser == null) {
            throw new Exception("Usuário deve estar logado");
        }
        var nUser = UserResponseDTO.updateUser(dto, oldUser);
        userRepository.save(nUser);
        var userP = UserResponseDTO.valueOf(nUser);

        return userP;

    }

    public UserResponseDTO updatePassword(AlterPasswordDTO dto, JwtAuthenticationToken token) throws Exception {

        var user = userRepository.findById(Long.parseLong(token.getName())).get();

        if (!passwordEncoder.matches(dto.oldPassword(), user.getPassword())) {
            throw new Exception("As senhas não coincidem");
        }
        user.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(user);
        return UserResponseDTO.valueOf(user);
    }

    public List<UserResponseDTO> listName(int paginas, int itens) {

        return userRepository.findAll(PageRequest.of(paginas, itens)).getContent().stream()
                .map(e -> UserResponseDTO.valueOf(e)).toList();
    }
}
