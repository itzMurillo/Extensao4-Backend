package br.unipar.devbackend.extensao4.service;

import br.unipar.devbackend.extensao4.model.Livro;
import br.unipar.devbackend.extensao4.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//classe de serviço com regras de negócio para livros
@Service
public class LivroService {

    //injeta o repository de livros
    @Autowired
    private LivroRepository livroRepository;

    //busca todos os livros do banco
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    //busca um livro específico pelo id
    public Optional<Livro> findById(Long id) {
        return livroRepository.findById(id);
    }

    //salva um novo livro no banco
    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    //atualiza os dados de um livro existente
    public Livro update(Long id, Livro detalhesLivro) {
        //busca o livro pelo id
        return livroRepository.findById(id)
                .map(book -> {
                    //atualiza os campos do livro
                    book.setTitulo(detalhesLivro.getTitulo());
                    book.setAutor(detalhesLivro.getAutor());
                    book.setDescricao(detalhesLivro.getDescricao());
                    book.setCapaUrl(detalhesLivro.getCapaUrl());
                    //salva as alterações no banco
                    return livroRepository.save(book);
                })
                //retorna null se o livro não for encontrado
                .orElse(null);
    }

    //remove um livro do banco pelo id
    public void delete(Long id) {
        livroRepository.deleteById(id);
    }
}