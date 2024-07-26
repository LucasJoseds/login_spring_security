package com.lucas.projeto_login.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.projeto_login.DTO.UserDTO;
import com.lucas.projeto_login.DTO.UserResponseDTO;
import com.lucas.projeto_login.Model.User;
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

    @PutMapping(value="/update/{id}")
    public ResponseEntity <UserResponseDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserResponseDTO dto) throws Exception{

        var userN = userService.updateUser(id, dto);
        return new ResponseEntity<UserResponseDTO>(userN, HttpStatus.CREATED);
    }

   
    @GetMapping(value="/list")
    public ResponseEntity listUsers(@RequestParam("paginas") int paginas , 
      @RequestParam("itens") int itens){

        var result = userService.listName(paginas,itens);

        return ResponseEntity.ok(result);
    }
    
}
