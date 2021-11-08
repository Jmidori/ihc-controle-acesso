package com.sga.backoffice.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColaboradorRequest {
    @JsonProperty
    private String cpf;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String email;
    @JsonProperty
    private boolean ativo;
    @JsonProperty
    private Long crachaId;
    @JsonProperty
    private Long perfilAcessoId;

    public ColaboradorRequest(String cpf, String nome, String email, boolean ativo, Long crachaId, Long perfilAcessoId) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.crachaId = crachaId;
        this.perfilAcessoId = perfilAcessoId;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAtivo() { return ativo; }

    public Long getCrachaId() {
        return crachaId;
    }

    public Long getPerfilAcessoId() {
        return perfilAcessoId;
    }
}
