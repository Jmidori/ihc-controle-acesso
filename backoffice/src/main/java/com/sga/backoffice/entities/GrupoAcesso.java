package com.sga.backoffice.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "grupo_acesso")
public class GrupoAcesso {

    @Id
    @GeneratedValue
    @Column(name="gap_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="pac_id", nullable=false)
    private PerfilAcesso perfilAcesso;

    @ManyToOne
    @JoinColumn(name="amb_id", nullable=false)
    private Ambiente ambiente;
}
