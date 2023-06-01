package com.example.prova_web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.prova_web.Model.Comida;
import com.example.prova_web.Service.ComidaService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {

    private ComidaService service;

    public MainController(ComidaService service){
        this.service = service;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getIndex(Model model){
        List<Comida> comidasList = service.findNotDeleteds();
        model.addAttribute("comidasList", comidasList);
        return "index";
    }

}
