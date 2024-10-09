package com.authorobra.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Autor{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull()
    private String nome;

    private String sexo;

    @Email(message = "E-mail invalido")
    private String email;

    @NotNull
    private LocalDate data_nasc;

    @NotNull
    private String pais_orig;
    @CPF(message = "CPF inválido")
    private String cpf;

    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL)
    private List<Obra> obras = new ArrayList<>();

    public Autor(Long id, String nome, String sexo, String email, LocalDate data_nasc, String pais_Orig, String cpf) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.data_nasc = data_nasc;
        pais_orig = pais_Orig;
        this.cpf = cpf;
    }

    public Autor() {
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

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
}
