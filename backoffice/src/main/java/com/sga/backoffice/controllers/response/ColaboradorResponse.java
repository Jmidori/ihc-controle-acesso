package com.sga.backoffice.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColaboradorResponse {
    @JsonProperty
    private Long id;
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

    public ColaboradorResponse(Long id, String cpf, String nome, String email, Long crachaId, Long perfilAcessoId) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.crachaId = crachaId;
        this.perfilAcessoId = perfilAcessoId;
    }

   public Long getId() {
        return id;
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
