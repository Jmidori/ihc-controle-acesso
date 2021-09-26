package com.sga.backoffice.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acesso")
public class Acesso {

    @Id @GeneratedValue
    @Column(name="acs_id")
    private Long id;

    @Column(name="acs_data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @ManyToOne
    @JoinColumn(name="cra_id", nullable=false)
    private Cracha cracha;

    @ManyToOne
    @JoinColumn(name = "amb_id", nullable = false)
    private Ambiente ambiente;

    public Acesso() {
    }

    public Acesso(Date dataHora, Cracha cracha, Ambiente ambiente) {
        this.dataHora = dataHora;
        this.cracha = cracha;
        this.ambiente = ambiente;
    }

    public Long getId() {
        return id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Cracha getCracha() {
        return cracha;
    }

    public void setCracha(Cracha cracha) {
        this.cracha = cracha;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }
}
