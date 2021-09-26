package com.sga.backoffice.entities;

import javax.persistence.*;

@Entity
@Table(name = "visitante")
public class Visitante {

    @Id
    @GeneratedValue
    @Column(name="vis_id")
    private Long id;

    @Column(name="vis_nome")
    private String nome;

    @Column(name="vis_rg")
    private String rg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cra_id", referencedColumnName = "cra_id")
    private Cracha cracha;
}
