package com.example.prova_web.Service;

import com.example.prova_web.Model.Comida;
import com.example.prova_web.Repository.ComidaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComidaService {
    private ComidaRepository repository;
    public ComidaService(ComidaRepository repository) {
        this.repository = repository;
    }

    //8) Implemente a rota de (“/salvar”) que deve receber dados através de método POST e cadastrar ou
    //atualizar um novo item no banco de dados. O método salvar deve validar os atributos do modelo. O
    //método salvar deve criar um nome único para a imagem enviada. Caso ocorra algum erro, a resposta
    //deve ser cancelada e o erro informado no formulário. Ao final do processo, a solicitação deve ser
    //redirecionada para “/admin” enviando uma mensagem de que a atualização ocorreu com sucesso.
    public void save(Comida p){
        repository.save(p);
    }
    public List<Comida> listAll(){
        return repository.findAll();
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
    public void update(Comida comida){
        repository.saveAndFlush(comida);

    }

    public List<Comida> findNotDeleteds(){
        List<Comida> list = repository.findAll();
        List<Comida> listaFinal = new ArrayList<>();
        for(Comida c : list){
            if(c.getDeleted() == null){
                listaFinal.add(c);
            }
        }
        return listaFinal;
    }

    public Optional<Comida> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
