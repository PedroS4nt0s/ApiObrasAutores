package com.authorobra.demo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

public class AutorDto {

    private Long id;
    @NotNull
    private String nome;
    private String sexo;
    private String email;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nasc;
    @NotNull
    private String pais_orig;
    private String cpf;

    public AutorDto(Long id, String nome, String sexo, String email, LocalDate data_nasc, String pais_orig, String cpf) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.data_nasc = data_nasc;
        this.pais_orig = pais_orig;
        this.cpf = cpf;
    }

    public AutorDto() {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(LocalDate data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getPais_orig() {
        return pais_orig;
    }

    public void setPais_orig(String pais_orig) {
        this.pais_orig = pais_orig;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
