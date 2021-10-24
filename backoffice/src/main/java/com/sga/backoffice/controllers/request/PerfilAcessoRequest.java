package com.sga.backoffice.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PerfilAcessoRequest {
    @JsonProperty
    private String nome;
    @JsonProperty
    private String descricao;

    public PerfilAcessoRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
