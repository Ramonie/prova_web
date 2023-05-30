package com.example.prova_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

    @GetMapping("/login")
    public String showLoginPage() {
        // Lógica do controlador para exibir a página de login
        return "login";
    }
}
