package com.pizzariaSoulcode.Pizzaria_Soulcode.models;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PedidoRequest {
    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "O ID da pizza é obrigatório")
    private Long pizzaId;

    private Long bebidaId;
    private List<Long> ingredienteId;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Long getBebidaId() {
        return bebidaId;
    }

    public void setBebidaId(Long bebidaId) {
        this.bebidaId = bebidaId;
    }

    public List<Long> getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(List<Long> ingredienteId) {
        this.ingredienteId = ingredienteId;
    }
}
