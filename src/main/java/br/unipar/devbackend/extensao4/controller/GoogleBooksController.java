package br.unipar.devbackend.extensao4.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

//controller REST pra buscar livros na api do GoogleBooks
@RestController
@RequestMapping("/api/googlebooks")
@CrossOrigin(origins = "http://localhost:4200")
public class GoogleBooksController {

    //utl base da api do GoogleBooks
    private static final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?q=";

    //endpoint pra buscar livros pelo termo de pesquisa
    @GetMapping("/search")
    public ResponseEntity<String> buscarLivros(@RequestParam String q) {
        try {
            RestTemplate restTemplate = new RestTemplate(); //cria um cliente http pra fazer requisições
            String url = GOOGLE_BOOKS_API + q.replace(" ", "+"); //monta a url substituindo espaços por +
            String resposta = restTemplate.getForObject(url, String.class); //faz a requisição pra api do GoogleBooks
            return ResponseEntity.ok(resposta); //return da resposta com sucesso
        } catch (Exception e) {
            //retorna erro caso a requisição falhe
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Erro ao consultar Google Books API\"}");
        }
    }
}