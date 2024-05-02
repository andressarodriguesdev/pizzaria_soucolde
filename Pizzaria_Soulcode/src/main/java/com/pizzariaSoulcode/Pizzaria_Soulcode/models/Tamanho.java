package com.pizzariaSoulcode.Pizzaria_Soulcode.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="tamanho")
public class Tamanho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTamanho;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private double desconto;

    public Long getIdTamanho() {
        return idTamanho;
    }

    public void setIdTamanho(Long idTamanho) {
        this.idTamanho = idTamanho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
}
