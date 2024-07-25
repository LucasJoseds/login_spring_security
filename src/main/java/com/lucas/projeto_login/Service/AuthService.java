
package com.lucas.projeto_login.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.projeto_login.DTO.LoginDTO;
import com.lucas.projeto_login.Model.User;
import com.lucas.projeto_login.Repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public String authentication(LoginDTO dto) {
        User user = userRepository.findByEmail(dto.email()).get();

        logger.info(user.getPassword());
        logger.info(dto.password());

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Error");
        }
        String token = tokenService.createToken(user);
        return token;

    }
}
