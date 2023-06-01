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
    //4) Implemente a rota de (“/index”) para, a partir de uma solicitação do tipo GET, gerar uma resposta
        //contendo no corpo um HTML que contém uma tabela ou similar de todos os itens (linhas) que estão
        //presentes no banco de dados e que não estão deletados (deleted == null). Note que a aplicação deve
        //utilizar uma técnica de soft-delete, ou seja, os itens jamais serão deletados de verdade do banco. Os
        //itens são deletados de maneira lógica quando deleted recebe a data atual. Você deve exibir a
        //imagem de cada um dos itens da lista. Para cada item listado adicione um link para a rota
        //“/adicionarCarrinho” passando como parâmetro para tal rota o ID do item escolhido. Por fim, adicione
        //na página gerada pela rota “/index” um link para a rota “/verCarrinho”.
    //11) Implemente a rota de (“/verCarrinho”) que ao receber uma solicitação do tipo GET lista todos os
    //itens que estão no atributo “carrinho da Sessão HTTP. Se o carrinho estiver vazio, redirecione a
    //resposta para “/index” enviando a mensagem de que não existem itens no carrinho. Por fim, adicione
    //um link para a rota “/finalizarCompra”.
    @GetMapping("/verCarrinho")
    public String exibirCarrinho(HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
        HttpSession session = request.getSession();
        long criacao = session.getCreationTime();
        //4- Adicione um cookie na resposta
        //chamado “visita” com a data e hora do acesso ao site. Esse cookie deve ser permanente e durar
        //24hs.
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
    // 4) rota “/adicionarCarrinho” passando como parâmetro para tal rota o ID do item escolhido.
    //10) Implemente a rota de (“/adicionarCarrinho”) que recebe uma solicitação do tipo GET contendo
    //como parâmetro o id de um dos itens. Realize uma busca no banco de dados pelo item a partir do ID
    //e adicione o objeto encontrado em uma Sessão HTTP no atributo “carrinho” que deve conter um
    //ArrayList de itens. Encaminhe a resposta para a rota “/index”. Atualize a página “index” para que seja
    //exibido a quantidade de itens no carrinho
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

    //12) Implemente a rota de (“/finalizarCompra”) que ao receber uma solicitação do tipo GET invalida a
    //Sessão existente e redireciona a resposta para “index”
    @GetMapping("/finalizarCompra")
    public String doFinalize(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        session.setAttribute("carrinho",null);
        redirectAttributes.addFlashAttribute("alerta","compra finalizada!");

        return "redirect:/index";

    }
}



