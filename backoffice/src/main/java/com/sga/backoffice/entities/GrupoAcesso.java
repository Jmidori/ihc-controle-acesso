package com.sga.backoffice.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "grupo_acesso")
public class GrupoAcesso implements Serializable {
    @Id
    @Column(name="gac_id")
    private String id;

    @ManyToOne
    @JoinColumn(name="pac_id", nullable=false)
    private PerfilAcesso perfilAcesso;

    @ManyToOne
    @JoinColumn(name="amb_id", nullable=false)
    private Ambiente ambiente;

    public GrupoAcesso() {
    }

    public GrupoAcesso(PerfilAcesso perfilAcesso, Ambiente ambiente) {
        this.id = String.valueOf(perfilAcesso.getId()).concat(String.valueOf(ambiente.getId()));
        this.perfilAcesso = perfilAcesso;
        this.ambiente = ambiente;
    }

    public String getId() {
        return id;
    }

    public PerfilAcesso getPerfilAcesso() {
        return perfilAcesso;
    }

    public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
        this.perfilAcesso = perfilAcesso;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }
}
