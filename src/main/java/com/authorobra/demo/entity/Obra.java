package com.authorobra.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "obra")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Size(max = 240, message = "Descriçao de no maximo 240 caracteres")
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataExposicao;

   /* @NotNull
    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "autor_obra",
        joinColumns = @JoinColumn(name = "obra_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id"))*/
   @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Obra(Long id, String nome, String descricao, LocalDate dataPublicacao, LocalDate dataExposicao, Autor autores) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.dataExposicao = dataExposicao;
        this.autor = autores;
    }

    public Obra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public LocalDate getDataExposicao() {
        return dataExposicao;
    }

    public void setDataExposicao(LocalDate dataExposicao) {
        this.dataExposicao = dataExposicao;
    }

    public Autor getAutores() {
        return autor;
    }

    public void setAutores(Autor autores) {
        this.autor = autores;
    }
}
