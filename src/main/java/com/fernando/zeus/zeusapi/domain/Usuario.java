package com.fernando.zeus.zeusapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String perfil;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Demanda> demandas;

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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<Demanda> getDemandas() {
        return demandas;
    }

    public void setDemandas(List<Demanda> demandas) {
        this.demandas = demandas;
    }
}
