package com.sga.backoffice.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cracha")
public class Cracha {
    @Id @GeneratedValue
    @Column(name = "cra_id")
    private Long id;

    @Column(name = "cra_ativo")
    private boolean ativo;

    @OneToOne(mappedBy = "cracha")
    private Colaborador colaborador;

    @OneToOne(mappedBy = "cracha")
    private Visitante visitante;

    @OneToMany(mappedBy="cracha")
    private Set<Acesso> acessos;

    public Cracha() {
    }

    public Cracha(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}

