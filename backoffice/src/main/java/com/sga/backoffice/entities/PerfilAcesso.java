package com.sga.backoffice.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "perfil_acesso")
public class PerfilAcesso {
    @Id
    @GeneratedValue
    @Column(name="pac_id")
    private Long id;

    @Column(name="pac_nome")
    private String nome;

    @Column(name="pac_descricao")
    private String descricao;

    @OneToOne(mappedBy = "perfilAcesso")
    private Colaborador colaborador;

    @OneToMany(mappedBy="perfilAcesso")
    private Set<GrupoAcesso> grupoAcessos;

    public PerfilAcesso() {
    }

    public PerfilAcesso(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
