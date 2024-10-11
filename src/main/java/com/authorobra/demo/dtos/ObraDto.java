package com.authorobra.demo.dtos;

import com.authorobra.demo.entity.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class ObraDto {

    private Long id;
    @NotNull
    private String nome;
    @NotNull
    @Size(max = 240, message = "Descrição de no máximo 240 caracteres")
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataExposicao;

    public ObraDto() {
    }

    public ObraDto(Long id, String nome, String descricao, LocalDate dataPublicacao, LocalDate dataExposicao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.dataExposicao = dataExposicao;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull @Size(max = 240, message = "Descrição de no máximo 240 caracteres") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull @Size(max = 240, message = "Descrição de no máximo 240 caracteres") String descricao) {
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

}

