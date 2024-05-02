package com.pizzariaSoulcode.Pizzaria_Soulcode.models;

import jakarta.persistence.*;

@Entity
@Table(name = "bebida_pedida")
public class BebidaPedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBebidaPedida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bebida", nullable = false)
    private Bebida bebida;

    @Column(nullable = false)
    private Integer quantidade;

    public Long getIdBebidaPedida() {
        return idBebidaPedida;
    }

    public void setIdBebidaPedida(Long idBebidaPedida) {
        this.idBebidaPedida = idBebidaPedida;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
