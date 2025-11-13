package br.unipar.devbackend.extensao4.controller;

import br.unipar.devbackend.extensao4.model.Livro;
import br.unipar.devbackend.extensao4.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller REST pra gerenciar livros salvos no banco
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/livros")
public class LivroController {

    //injeta o repository de livros
    @Autowired
    private LivroRepository livroRepository;

    //endpoint pra salvar um novo livro
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Livro livro) {
        try {
            System.out.println("📘 Recebido do Angular: " + livro); //loga o livro recebido do frontend
            Livro salvo = livroRepository.save(livro); //salva o livro no banco de dados
            System.out.println("✅ Livro salvo com ID: " + salvo.getId()); //loga o id do livro salvo
            return ResponseEntity.ok(salvo); //return do livro salvo com sucesso
        } catch (Exception e) {
            System.err.println("❌ Erro ao salvar livro: " + e.getMessage());  //loga o erro no console
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //return do erro ao frontend
                    .body("Erro ao salvar livro: " + e.getMessage());
        }
    }

    //endpoint pra listar todos os livros salvos
    @GetMapping
    public List<Livro> listar() {
        return livroRepository.findAll(); //return de todos os livros do banco
    }

    //endpoint pra deletar um livro pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            //verifica se o livro existe no banco
            if (livroRepository.existsById(id)) {
                //remove o livro do banco
                livroRepository.deleteById(id);
                //loga a remoção
                System.out.println("🗑️ Livro removido com ID: " + id);
                //return da resposta sem conteúdo (sucesso)
                return ResponseEntity.noContent().build();
            } else {
                //loga que o livro não foi encontrado
                System.out.println("⚠️ Livro não encontrado para ID: " + id);
                //retorna erro 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Livro não encontrado com ID: " + id);
            }
        } catch (Exception e) {
            //loga o erro no console
            System.err.println("❌ Erro ao remover livro: " + e.getMessage());
            //retorna erro ao frontend
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao remover livro: " + e.getMessage());
        }
    }
}