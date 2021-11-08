package com.sga.backoffice.entities;

import javax.persistence.*;

@Entity
@Table(name = "colaborador")
public class Colaborador {

    @Id @GeneratedValue
    @Column(name="col_id")
    private Long id;

    @Column(name="col_cpf")
    private String cpf;

    @Column(name = "col_nome")
    private String nome;

    @Column(name = "col_email")
    private String email;

    @Column(name = "col_ativo")
    private boolean ativo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cra_id", referencedColumnName = "cra_id")
    private Cracha cracha;

    @ManyToOne
    @JoinColumn(name = "pac_id", referencedColumnName = "pac_id")
    private PerfilAcesso perfilAcesso;

    @OneToOne(mappedBy = "colaborador")
    private Usuario usuario;

    public Colaborador() {
    }

    public Colaborador(Long id, String cpf, String nome, String email, boolean ativo, Cracha cracha, PerfilAcesso perfilAcesso) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.cracha = cracha;
        this.perfilAcesso = perfilAcesso;
    }

    public Colaborador(String cpf, String nome, String email, boolean ativo, Cracha cracha, PerfilAcesso perfilAcesso) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.cracha = cracha;
        this.perfilAcesso = perfilAcesso;
    }

    public Long getId() {
        return id;
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

    public Cracha getCracha() {
        return cracha;
    }

    public void setCracha(Cracha cracha) {
        this.cracha = cracha;
    }

    public PerfilAcesso getPerfilAcesso() {
        return perfilAcesso;
    }

    public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
        this.perfilAcesso = perfilAcesso;
    }
}
