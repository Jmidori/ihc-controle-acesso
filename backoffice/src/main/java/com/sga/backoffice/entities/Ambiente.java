package com.sga.backoffice.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ambiente")
public class Ambiente {

    @Id
    @GeneratedValue
    @Column(name="amb_id")
    private Long id;

    @Column(name = "amb_nome")
    private String nome;

    @Column(name = "amb_descricao")
    private String descricao;

    @Column(name = "amb_nivel_acesso")
    private int nivelAcesso;

    @OneToMany(mappedBy="ambiente")
    private Set<Acesso> acessos;

    @OneToMany(mappedBy="ambiente")
    private Set<GrupoAcesso> grupoAcessos;

    public Ambiente() {
    }

    public Ambiente(String nome, String descricao, int nivelAcesso, Set<Acesso> acessos) {
        this.nome = nome;
        this.descricao = descricao;
        this.nivelAcesso = nivelAcesso;
        this.acessos = acessos;
    }

    public Long getId() {
        return id;
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

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Set<Acesso> getAcessos() {
        return acessos;
    }

    public void setAcessos(Set<Acesso> acessos) {
        this.acessos = acessos;
    }
}
