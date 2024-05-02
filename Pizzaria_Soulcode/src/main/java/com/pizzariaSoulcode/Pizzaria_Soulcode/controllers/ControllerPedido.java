package com.pizzariaSoulcode.Pizzaria_Soulcode.controllers;

import com.pizzariaSoulcode.Pizzaria_Soulcode.models.Pedido;
import com.pizzariaSoulcode.Pizzaria_Soulcode.models.PedidoRequest;
import com.pizzariaSoulcode.Pizzaria_Soulcode.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class ControllerPedido {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping("/criar")
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoRequest pedidoRequest) {
        Pedido novoPedido = pedidoService.processarPedido(pedidoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable("id") Long id) {
        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable("id") Long id, @RequestBody Pedido pedidoAtualizado) {
        Pedido pedido = pedidoService.atualizarPedido(id, pedidoAtualizado);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable("id") Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }

}
