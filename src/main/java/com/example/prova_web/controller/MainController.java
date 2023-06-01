package com.example.prova_web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.prova_web.Model.Comida;
import com.example.prova_web.Service.ComidaService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private ComidaService service;

    public MainController(ComidaService service){
        this.service = service;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getIndex(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Comida> comidas = (List<Comida>)session.getAttribute("carrinho");
        if(comidas == null){
            comidas = new ArrayList<>();
        }

        List<Comida> comidasList = service.findNotDeleteds();
        model.addAttribute("comidasList", comidasList);
        model.addAttribute("qtdCarrinho", comidas.size());
        return "index";
    }

}
