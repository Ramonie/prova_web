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
    //4) Implemente a rota de (“/index”) para, a partir de uma solicitação do tipo GET, gerar uma resposta
    //contendo no corpo um HTML que contém uma tabela ou similar de todos os itens (linhas) que estão
    //presentes no banco de dados e que não estão deletados (deleted == null). Note que a aplicação deve
    //utilizar uma técnica de soft-delete, ou seja, os itens jamais serão deletados de verdade do banco. Os
    //itens são deletados de maneira lógica quando deleted recebe a data atual. Você deve exibir a
    //imagem de cada um dos itens da lista. Para cada item listado adicione um link para a rota
    //“/adicionarCarrinho” passando como parâmetro para tal rota o ID do item escolhido. Por fim, adicione
    //na página gerada pela rota “/index” um link para a rota “/verCarrinho”. Adicione um cookie na resposta
    //chamado “visita” com a data e hora do acesso ao site. Esse cookie deve ser permanente e durar
    //24hs.
    // Página inicial ("/" ou "/index")
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
