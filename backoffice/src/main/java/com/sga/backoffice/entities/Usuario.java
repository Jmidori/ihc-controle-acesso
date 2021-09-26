package com.sga.backoffice.entities;

import javax.persistence.*;

@Entity
@Table(name = "usuario_backoffice")
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name="ubk_id")
    private Long id;

    @Column(name="ubk_admin")
    private boolean admin;

    @Column(name = "ubk_recepcao")
    private boolean recepcao;

    @Column(name = "ubk_senha")
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "col_id", referencedColumnName = "col_id")
    private Colaborador colaborador;

    public Usuario() {
    }

    public Usuario(boolean admin, boolean recepcao, String senha, Colaborador colaborador) {
        this.admin = admin;
        this.recepcao = recepcao;
        this.senha = senha;
        this.colaborador = colaborador;
    }

    public Long getId() {
        return id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
