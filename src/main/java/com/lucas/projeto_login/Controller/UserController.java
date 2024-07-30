package com.lucas.projeto_login.Controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.projeto_login.DTO.AlterPasswordDTO;
import com.lucas.projeto_login.DTO.UserDTO;
import com.lucas.projeto_login.DTO.UserResponseDTO;
import com.lucas.projeto_login.Service.UserService;

@RestController
@RequestMapping(value="/api/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value="/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDTO dto){

            var userCreated = userService.create(dto);   
            return new ResponseEntity(userCreated,HttpStatus.CREATED); 

    }

    @PutMapping(value="/update")
    public ResponseEntity <UserResponseDTO> updateUser(@RequestBody UserResponseDTO dto, JwtAuthenticationToken token) throws Exception{

        var userN = userService.updateUserlogged(dto,token);
        return new ResponseEntity<UserResponseDTO>(userN, HttpStatus.CREATED);
    }

    @PatchMapping(value="/updatePassword")
    public ResponseEntity<UserResponseDTO> updatePasswordUser(@RequestBody AlterPasswordDTO dto, JwtAuthenticationToken token) throws Exception{

        var changedUser = userService.updatePassword(dto, token);
 
        return ResponseEntity.ok(changedUser);
    }

   
    @GetMapping(value="/list")
    public ResponseEntity listUsers(@RequestParam("paginas") int paginas , 
      @RequestParam("itens") int itens){

        var result = userService.listName(paginas,itens);

        return ResponseEntity.ok(result);
    }
    
}
