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
    private boolean ativo;
    @JsonProperty
    private Long crachaId;
    @JsonProperty
    private Long perfilAcessoId;

    public ColaboradorResponse() {
    }

    public ColaboradorResponse(Long id, String cpf, String nome, String email, boolean ativo, Long crachaId, Long perfilAcessoId) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.crachaId = crachaId;
        this.perfilAcessoId = perfilAcessoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getCrachaId() {
        return crachaId;
    }

    public void setCrachaId(Long crachaId) {
        this.crachaId = crachaId;
    }

    public Long getPerfilAcessoId() {
        return perfilAcessoId;
    }

    public void setPerfilAcessoId(Long perfilAcessoId) {
        this.perfilAcessoId = perfilAcessoId;
    }
}
