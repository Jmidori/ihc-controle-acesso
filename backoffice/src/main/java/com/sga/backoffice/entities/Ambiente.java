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

    @Column(name = "amb_sentido_fluxo")
    private String sentidoFluxo;

    @Column(name = "amb_nivel_acesso")
    private int nivelAcesso;

    public Ambiente() {
    }

    public Ambiente(Long id, String nome, String descricao, String sentidoFluxo, int nivelAcesso) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.sentidoFluxo = sentidoFluxo;
        this.nivelAcesso = nivelAcesso;
    }

    public Ambiente(String nome, String descricao, String sentidoFluxo, int nivelAcesso) {
        this.nome = nome;
        this.descricao = descricao;
        this.sentidoFluxo = sentidoFluxo;
        this.nivelAcesso = nivelAcesso;
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

    public String getSentidoFluxo() {
        return sentidoFluxo;
    }

    public void setSentidoFluxo(String sentidoFluxo) {
        this.sentidoFluxo = sentidoFluxo;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

}
