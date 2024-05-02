package com.pizzariaSoulcode.Pizzaria_Soulcode.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "fornada")
public class Fornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFornada;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInicio;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraFim;

    public Long getIdFornada() {
        return idFornada;
    }

    public void setIdFornada(Long idFornada) {
        this.idFornada = idFornada;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
}
