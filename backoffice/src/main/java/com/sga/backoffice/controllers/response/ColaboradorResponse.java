package com.sga.backoffice.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColaboradorResponse {
    @JsonProperty
    private String cpf;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String email;
    @JsonProperty
    private Long crachaId;
    @JsonProperty
    private Long perfilAcessoId;

    public ColaboradorResponse() {
    }

    public ColaboradorResponse(String cpf, String nome, String email, Long crachaId, Long perfilAcessoId) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
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

    public Long getCrachaId() {
        return crachaId;
    }

    public Long getPerfilAcessoId() {
        return perfilAcessoId;
    }
}
