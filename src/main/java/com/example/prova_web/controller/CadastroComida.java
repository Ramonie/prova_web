package com.example.prova_web.controller;

import com.example.prova_web.Model.Comida;
import com.example.prova_web.Service.CadastroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CadastroComida {
    @GetMapping("/cadrastoComidas")
    public String CadastroComida() {return "cadastroComidas";
    }
}
