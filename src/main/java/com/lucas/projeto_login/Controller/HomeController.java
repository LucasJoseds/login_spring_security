package com.lucas.projeto_login.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/home")
public class HomeController {


    @GetMapping(value="/user")
    public String pageUser(){

        return "Página de Usuário";
    }


    @GetMapping(value="/admin")
    public String pageAdmin(){

        return "Página de Admin";
    }

}
