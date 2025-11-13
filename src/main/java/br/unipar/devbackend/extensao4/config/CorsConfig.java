package br.unipar.devbackend.extensao4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//classe de configuração do Spring
@Configuration
//permite requisições do frontend rodando na porta 4200
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CorsConfig {

    //cria um bean pra configurar o CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //aplica a configuração CORS pra todas as rotas
                registry.addMapping("/**")
                        //define qual origem pode acessar a API
                        .allowedOrigins("http://localhost:4200") //define quais métodos HTTP são permitidos
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //permite todos os cabeçalhos nas requisições
                        .allowedHeaders("*") //permite envio de credenciais (cookies, tokens...)
                        .allowCredentials(true);
            }
        };
    }
}