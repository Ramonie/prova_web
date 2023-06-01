package com.example.prova_web.controller;

import com.example.prova_web.Model.Comida;
import com.example.prova_web.Service.ComidaService;
import com.example.prova_web.Service.FileStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class ComidaController {
    @Autowired
    private ComidaService service;

    @Autowired
    private FileStorageService fileStorageService;


    @GetMapping("/admin")
    public String doAdm(Model model){
        List<Comida> comidaList = service.findNotDeleteds();
        model.addAttribute("comidasList",comidaList);
        return "admin";
    }
    @GetMapping("/cadastroComidas")
    public String cadastro(Model model) {
        model.addAttribute("comida",new Comida());
        return "cadastroComidas";
    }

    @PostMapping("/salvar")
    public String doSave(@ModelAttribute @Valid Comida c, Errors errors, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file){
        if(errors.hasErrors()){
            return "cadastroComidas";
        }else{
            c.setImageUri(fileStorageService.save(file));
            redirectAttributes.addFlashAttribute("alerta", "Comida " + (c.getId() != null ? "editada" : "cadastrada") + " com sucesso!");
            service.save(c);

            return "redirect:/admin";
        }
    }
    @GetMapping("/deletar/{id}")
    public String doDelete( @PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes){
        Comida comida = service.findById(id).get();
        comida.setDeleted(new Date());
        service.update(comida);
        redirectAttributes.addFlashAttribute("alerta", "Comida excluída com sucesso!");
        return "redirect:/admin";
    }

    @GetMapping("/editar/{id}")
    public String doEdit( @PathVariable(name = "id") Long id, Model model){
        Comida comida = service.findById(id).get();
        model.addAttribute("comida",comida);
        return "cadastroComidas";
    }
}
