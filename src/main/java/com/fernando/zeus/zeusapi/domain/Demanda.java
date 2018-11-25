package com.fernando.zeus.zeusapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Demanda {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String descricao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Date dataCadastro;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Date dataInicio;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Date dataFimPrevisão;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Usuario cliente;


    public Demanda(String nome) {
        this.nome = nome;
    }

    public Demanda(){}

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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFimPrevisão() {
        return dataFimPrevisão;
    }

    public void setDataFimPrevisão(Date dataFimPrevisão) {
        this.dataFimPrevisão = dataFimPrevisão;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }
}
