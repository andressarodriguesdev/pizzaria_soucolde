package com.pizzariaSoulcode.Pizzaria_Soulcode.services;

import com.pizzariaSoulcode.Pizzaria_Soulcode.models.*;
import com.pizzariaSoulcode.Pizzaria_Soulcode.repositores.BebidaRepository;
import com.pizzariaSoulcode.Pizzaria_Soulcode.repositores.ClienteRepository;
import com.pizzariaSoulcode.Pizzaria_Soulcode.repositores.IngredienteRepository;
import com.pizzariaSoulcode.Pizzaria_Soulcode.repositores.PedidoRepository;
import com.pizzariaSoulcode.Pizzaria_Soulcode.repositores.PizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public Pedido processarPedido(PedidoRequest pedidoRequest) {
        Long clienteId = pedidoRequest.getClienteId();
        Long pizzaId = pedidoRequest.getPizzaId();

        // Verificar se os IDs do cliente e da pizza foram fornecidos
        if (clienteId == null || pizzaId == null) {
            throw new IllegalArgumentException("IDs de cliente e pizza são obrigatórios.");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + clienteId));

        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new EntityNotFoundException("Pizza não encontrada com o ID: " + pizzaId));

        Bebida bebida = null;
        Long bebidaId = pedidoRequest.getBebidaId();
        if (bebidaId != null) {
            bebida = bebidaRepository.findById(bebidaId)
                    .orElseThrow(() -> new EntityNotFoundException("Bebida não encontrada com o ID: " + bebidaId));
        }

        List<Long> ingredientesIds = pedidoRequest.getIngredienteId();
        List<Ingrediente> ingredientes = ingredientesIds.stream()
                .map(ingredienteId -> ingredienteRepository.findById(ingredienteId)
                        .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado com o ID: " + ingredienteId)))
                .collect(Collectors.toList());

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setPizza(pizza);
        pedido.setBebida(bebida);
        pedido.setIngredientes(ingredientes);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + id));
    }

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com o ID: " + id));

        // Atualizar os dados do pedido com base no pedidoAtualizado recebido
        pedido.setCliente(pedidoAtualizado.getCliente());
        pedido.setPizza(pedidoAtualizado.getPizza());
        pedido.setBebida(pedidoAtualizado.getBebida());
        pedido.setIngredientes(pedidoAtualizado.getIngredientes());

        // Salvar e retornar o pedido atualizado
        return pedidoRepository.save(pedido);
    }

    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido não encontrado com o ID: " + id);
        }
        pedidoRepository.deleteById(id);
    }
}
