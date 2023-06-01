package com.example.prova_web.Service;

import com.example.prova_web.Model.Usuario;
import com.example.prova_web.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

//2) Crie a classe do usuário (id, username, password, isAdmin, etc) que deve implementar a interface
//UserDetails. Na aplicação os usuários poderão assumir 2 papeis (roles) “ROLE_ADMIN” e
//“ROLE_USER”.
@Service
public class UsuarioService implements UserDetailsService {
    UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> user = repository.findByLogin(username);
        if (user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public List<Usuario> listAll(){
        return  repository.findAll();
    }
}
