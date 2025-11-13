package br.unipar.devbackend.extensao4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//classe de configuração de segurança do Spring
@Configuration
public class SecurityConfig {

    //cria um bean pra configurar a segurança da aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //desabilita proteção CSRF (Cross-Site Request Forgery)
                .authorizeHttpRequests(auth -> auth //define regras de autorização para as requisições
                        .requestMatchers("/api/googlebooks/**", "/livros/**").permitAll() //permite acesso público às rotas da API de livros
                        .anyRequest().authenticated()  //todas as outras rotas exigem autenticação
                )
                .formLogin().disable() //desabilita o formulário de login padrão do Spring
                .httpBasic(); //habilita autenticação basica HTTP (usuario e senha)
        return http.build(); //return da configuração construida
    }
}