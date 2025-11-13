package br.unipar.devbackend.extensao4.repository;

import br.unipar.devbackend.extensao4.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//interface de repository pra acessar dados de livros no banco
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}