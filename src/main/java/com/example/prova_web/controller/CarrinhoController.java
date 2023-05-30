package com.example.prova_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarrinhoController {
        private List<Object> carrinho = new ArrayList<>();

        @GetMapping("/verCarrinho")
        public String exibirCarrinho(Model model) {
            model.addAttribute("carrinho", carrinho);
            return "verCarrinho";
        }

        @GetMapping("/adicionarCarrinho")
        public String adicionarAoCarrinho(@RequestParam("id") Long id) {
            // Lógica para adicionar o item ao carrinho usando o ID fornecido
            Object Item = getItemById(id);
            if (Item != null) {
                carrinho.add(Item);
            }
            return "redirect:/verCarrinho"; // Redireciona para a página do carrinho
        }

        // Método auxiliar para obter um item pelo ID (substitua com sua própria implementação)
        private Object getItemById(Long id) {
            // Implemente a lógica para buscar o item no banco de dados ou em algum outro lugar
            // com base no ID fornecido
            // Retorne o item se for encontrado, caso contrário, retorne null
            // Exemplo:
            if (id == 1) {
                return new Item(1, "Item 1", 10.99);
            } else if (id == 2) {
                return new Item(2, "Item 2", 15.99);
            }
            return null;
        }
}


