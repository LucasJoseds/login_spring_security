package com.lucas.projeto_login.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/home")
public class HomeController {


    @GetMapping(value="/user")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER', 'SCOPE_ADMIN')")
    public String pageUser(){

        return "Página de Usuário";
    }

    @GetMapping(value="/admin")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String pageAdmin(){

        return "Página de Admin";
    }

}
