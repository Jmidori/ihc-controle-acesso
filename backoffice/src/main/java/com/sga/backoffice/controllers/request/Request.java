package com.sga.backoffice.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    @JsonProperty
    private Long perfilAcessoId;
    @JsonProperty
    private Long ambienteId;

    public Request(Long perfilAcessoId, Long ambienteId) {
        this.perfilAcessoId = perfilAcessoId;
        this.ambienteId = ambienteId;
    }

    public Long getPerfilAcessoId() {
        return perfilAcessoId;
    }

    public Long getAmbienteId() {
        return ambienteId;
    }
}
