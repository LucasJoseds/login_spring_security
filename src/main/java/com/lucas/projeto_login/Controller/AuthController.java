package com.lucas.projeto_login.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.projeto_login.DTO.LoginDTO;
import com.lucas.projeto_login.DTO.LoginResponseDTO;
import com.lucas.projeto_login.Service.AuthService;

@RestController
@RequestMapping(value="/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
       this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> auth(@RequestBody LoginDTO dto) throws Exception{

            var tokenResult = authService.authentication(dto);
            var user = authService.getUser(dto);
            return new ResponseEntity<LoginResponseDTO>(new LoginResponseDTO(tokenResult,user),HttpStatus.CREATED);
    }
    
}
