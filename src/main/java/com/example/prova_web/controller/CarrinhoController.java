package com.example.prova_web.controller;

import com.example.prova_web.Model.Comida;
import com.example.prova_web.Service.ComidaService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CarrinhoController {
    @Autowired
    private ComidaService service;

    @GetMapping("/verCarrinho")
    public String exibirCarrinho(HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
        HttpSession session = request.getSession();
        long criacao = session.getCreationTime();

        Date data = new Date(criacao);
        Cookie cookie = new Cookie("visita", "" + data.getTime());
        cookie.setMaxAge(60 * 60 * 24);

        response.addCookie(cookie);
        List<Comida> comidas = (List<Comida>) session.getAttribute("carrinho");
        if(comidas == null){
            comidas = new ArrayList<>();
        }

        if (comidas.isEmpty()) {
            redirectAttributes.addFlashAttribute("alerta", "Carrinho está vazio!");
            return "redirect:/index";
        } else {
            Double total = comidas.stream()
                    .mapToDouble(Comida::getPreco)
                    .reduce(0.0, Double::sum);
            model.addAttribute("total", total);
            model.addAttribute("carrinhoComidas", comidas);
            return "verCarrinho";
        }
    }

    @GetMapping("/adicionarCarrinho/{id}")
    public String doCarrinho(@PathVariable(name = "id") Long id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        List<Comida> carrinho = (List<Comida>) session.getAttribute("carrinho");
        Comida comida = service.findById(id).get();
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }
        carrinho.add(comida);
        redirectAttributes.addFlashAttribute("alerta", "Comida adicionada ao carrinho!");
        session.setAttribute("carrinho", carrinho);

        return "redirect:/index";
    }

    @GetMapping("/finalizarCompra")
    public String doFinalize(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        session.setAttribute("carrinho",null);
        redirectAttributes.addFlashAttribute("alerta","compra finalizada!");

        return "redirect:/index";

    }
}



