package com.sga.backoffice.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sga.backoffice.entities.Colaborador;

public class UsuarioSignupRequest {
    @JsonProperty
    private Colaborador colaborador;
    @JsonProperty
    private String password;
    @JsonProperty
    private boolean admin;
    @JsonProperty
    private boolean recepcao;

    public UsuarioSignupRequest(){}

    public UsuarioSignupRequest(Colaborador colaborador, String password, boolean admin, boolean recepcao) {
        this.colaborador = colaborador;
        this.password = password;
        this.admin = admin;
        this.recepcao = recepcao;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isRecepcao() {
        return recepcao;
    }

    public void setRecepcao(boolean recepcao) {
        this.recepcao = recepcao;
    }
}
