package com.sga.backoffice.entities;

import javax.persistence.*;
import java.util.List;
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

    @OneToMany
    @JoinColumn(name = "col_id")
    private List<Colaborador> colaboradores;

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

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
