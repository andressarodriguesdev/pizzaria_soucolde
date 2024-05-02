package com.pizzariaSoulcode.Pizzaria_Soulcode.models;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngrediente;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private double preco;

    public Ingrediente() {
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
