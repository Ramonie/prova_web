package com.example.prova_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//13) Adicione o Spring Security ao seu projeto e implemente o UserDetails Service. Configure a
//aplicação para que sejam cadastrados automaticamente 3 usuários sempre que a aplicação for
//executada, sendo eles um administrador e os outros dois usuários padrão. Configure o Spring
//Security para realizar a autenticação com base no seu UserDetails Service (que busca no banco de
//dados). Utilize BCrypt para codificar a senha. Configure as rotas para que um usuário não logado
//possa acessar a página de “/login” “/index”. Apenas um usuário com o papel “ROLE_ADMIN” poderá
//acessar as páginas “/admin” “/cadastro” “/salvar” “/editar” e “/deletar”. Apenas um usuário com o papel
//“ROLE_USER” poderá acessar as páginas “/vercarrinho”, “/adicionarcarrinho”, “/finalizarcompra”.
//Adicione o username do usuário logado no cabeçalho da página. Adicione um botão para “logout” no
//cabeçalho da página.
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/login","/", "/index","/assets/*", "/css/","/images/", "/js/*").permitAll()
                        .requestMatchers("/admin", "/cadastroComidas", "/salvar", "/editar/{id}", "/deletar/{id}").hasRole("ADMIN")
                        .requestMatchers("/verCarrinho", "/adicionarCarrinho/{id}", "/finalizarCompra").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/", true)
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                ).exceptionHandling((exception) -> exception
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/");
                })
        );;
        return http.build();
    }

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


        /*
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}password")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/

}
