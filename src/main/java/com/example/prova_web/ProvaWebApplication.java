package com.example.prova_web;

import com.example.prova_web.Model.Usuario;
import com.example.prova_web.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ProvaWebApplication implements WebMvcConfigurer {
	@Bean
	CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
		return args -> {

//			List<Usuario> users = Stream.of(
//					new Usuario("1", "João", "123.456.789-10", "admin", encoder.encode("admin"), true),
//					new Usuario("2", "Maria", "444.456.789-10", "user1", encoder.encode("user1"), false),
//					new Usuario("3", "Pedro", "555.456.789-10", "user2", encoder.encode("user2"), false)
//			).collect(Collectors.toList());
//
//			for (var e : users) {
//				System.out.println(e);
//			}
//			usuarioRepository.saveAll(users);
		};
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
	}





	public static void main(String[] args) {
		SpringApplication.run(ProvaWebApplication.class, args);
	}

}
