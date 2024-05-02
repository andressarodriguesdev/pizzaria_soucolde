package com.pizzariaSoulcode.Pizzaria_Soulcode.models;


import jakarta.persistence.*;


import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "bebida")
public class Bebida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBebida;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private double preco;


    public Bebida() {
        // Construtor padrão vazio requerido pelo Hibernate
    }

    public boolean isPrecoValido() {
        return preco > 0 && preco <= 100.00;
    }
    

    public Long getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(Long idBebida) {
        this.idBebida = idBebida;
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



    // Método para atualizar o preço
    public void atualizarPreco(double novoPreco) {
        if (novoPreco <= 0 || novoPreco > 100.00) {
            throw new IllegalArgumentException("Preço inválido: deve ser entre R$ 0,00 e R$ 100,00");
        }



        this.preco = novoPreco;
    }


}

