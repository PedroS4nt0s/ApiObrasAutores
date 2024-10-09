package com.authorobra.demo.dtos;

import com.authorobra.demo.entity.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

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
    private List<Autor> autoresIds;

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

    public List<Autor> getAutoresIds() {
        return autoresIds;
    }

    public void setAutoresIds(List<Autor> autoresIds) {
        this.autoresIds = autoresIds;
    }
}
