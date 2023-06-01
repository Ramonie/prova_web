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

    public void save(Comida p){
        repository.save(p);
    }
    public List<Comida> listAll(){
        return repository.findAll();
    }

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
