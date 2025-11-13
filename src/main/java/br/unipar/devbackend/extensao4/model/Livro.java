package br.unipar.devbackend.extensao4.model;

import jakarta.persistence.*;
import lombok.*;

//clase da entidade livro
@Entity
@Data
public class Livro {

    //atributos da classe
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String descricao;
    private String capaUrl;
    private Double preco;
    private Integer ano;
}