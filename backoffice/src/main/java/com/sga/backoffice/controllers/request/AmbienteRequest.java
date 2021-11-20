package com.sga.backoffice.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AmbienteRequest {
    @JsonProperty
    private String nome;

    @JsonProperty
    private String descricao;

    @JsonProperty
    private String sentidoFluxo;

    @JsonProperty
    private int nivelAcesso;

    public AmbienteRequest(String nome, String descricao, String sentidoFluxo, int nivelAcesso) {
        this.nome = nome;
        this.descricao = descricao;
        this.sentidoFluxo = sentidoFluxo;
        this.nivelAcesso = nivelAcesso;
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

    public String getSentidoFluxo() {
        return sentidoFluxo;
    }

    public void setSentidoFluxo(String sentidoFluxo) {
        this.sentidoFluxo = sentidoFluxo;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
