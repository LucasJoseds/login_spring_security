
package com.lucas.projeto_login.Service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.projeto_login.DTO.LoginDTO;
import com.lucas.projeto_login.DTO.UserResponseDTO;
import com.lucas.projeto_login.Model.User;
import com.lucas.projeto_login.Repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    //Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }



    public String authentication(LoginDTO dto) {
        User user = userRepository.findByEmail(dto.email()).get();

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Error");
        }
        String token = tokenService.createToken(user);
        return token;

    }
    public UserResponseDTO getUser(LoginDTO dto) throws Exception{

        var user = userRepository.findByEmail(dto.email()).get();
        if(user==null){
            throw new Exception("E-mail não encontrado");
        }  
        return UserResponseDTO.valueOf(user);

    }


}
