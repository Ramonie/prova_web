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

    //5) Implemente a rota de (“/admin”) para, a partir de uma solicitação do tipo GET, gerar uma resposta
    //contendo no corpo um HTML que contém uma tabela de todos os itens (linhas) que estão presentes
    //no banco de dados e que não estão deletados (deleted == null). Para cada item listado adicione um
    //link para a rota “/editar” e “/deletar” passando como parâmetro para tal rota o ID do item escolhido.
    //Por fim, adicione na página gerada pela rota “/admin” um link para a rota “/cadastro”
    @GetMapping("/admin")
    public String doAdm(Model model){
        List<Comida> comidaList = service.findNotDeleteds();
        model.addAttribute("comidasList",comidaList);
        return "admin";
    }
    //6) Implemente a rota de (“/cadastro”) para, a partir de uma solicitação do tipo GET, gerar uma
    //resposta contendo no corpo um formulário HTML para cadastro de um item do seu tema. O formulário
    //deve conter um input de envio de arquivos para envio da imagem. O formulário deve conter tag para
    //tratamento de erros utilizando o Thymeleaf. O formulário deve enviar os dados da solicitação através
    //do método POST para a rota “/salvar”.


    @GetMapping("/cadastroComidas")
    public String cadastro(Model model) {
        model.addAttribute("comida",new Comida());
        return "cadastroComidas";
    }

    //7) Implemente a rota de (“/editar”) para, a partir de uma solicitação do tipo GET, gerar uma resposta
    //contendo no corpo um formulário HTML para edição de um item do seu tema. Os dados do formulário
    //devem estar preenchidos com os dados daquele item no banco. O formulário deve conter tag para
    //tratamento de erros utilizando o Thymeleaf. O formulário deve enviar os dados da solicitação através
    //do método POST para a rota “/salvar”. Ao final do processo, a solicitação deve ser redirecionada para
    //“/admin” enviando uma mensagem de que a atualização ocorreu com sucesso.
    //8) Implemente a rota de (“/salvar”) que deve receber dados através de método POST e cadastrar ou
    //atualizar um novo item no banco de dados. O método salvar deve validar os atributos do modelo. O
    //método salvar deve criar um nome único para a imagem enviada. Caso ocorra algum erro, a resposta
    //deve ser cancelada e o erro informado no formulário. Ao final do processo, a solicitação deve ser
    //redirecionada para “/admin” enviando uma mensagem de que a atualização ocorreu com sucesso.
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
    // 9) Implemente a rota de (“/deletar”) que deve receber dados através de método GET e atualizar um
    //item no banco de dados para que o atributo Deleted contenha a data (Long) atual. Dessa forma, a
    //operação de remoção de um registro será feita através de um soft delete, onde o registro do banco de
    //dados não será apagado de fato. Ao final do processo, a solicitação deve ser redirecionada para
    //“/index” enviando uma mensagem de que a remoção ocorreu com sucesso.
    @GetMapping("/deletar/{id}")
    public String doDelete( @PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes){
        Comida comida = service.findById(id).get();
        comida.setDeleted(new Date());
        service.update(comida);
        redirectAttributes.addFlashAttribute("alerta", "Comida excluída com sucesso!");
        return "redirect:/admin";
    }
    //7) Implemente a rota de (“/editar”) para, a partir de uma solicitação do tipo GET, gerar uma resposta
    //contendo no corpo um formulário HTML para edição de um item do seu tema. Os dados do formulário
    //devem estar preenchidos com os dados daquele item no banco. O formulário deve conter tag para
    //tratamento de erros utilizando o Thymeleaf. O formulário deve enviar os dados da solicitação através
    //do método POST para a rota “/salvar”. Ao final do processo, a solicitação deve ser redirecionada para
    //“/admin” enviando uma mensagem de que a atualização ocorreu com sucesso.
    @GetMapping("/editar/{id}")
    public String doEdit( @PathVariable(name = "id") Long id, Model model){
        Comida comida = service.findById(id).get();
        model.addAttribute("comida",comida);
        return "cadastroComidas";
    }
}
