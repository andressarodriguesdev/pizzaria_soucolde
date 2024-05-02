package com.pizzariaSoulcode.Pizzaria_Soulcode.controllers;


import com.pizzariaSoulcode.Pizzaria_Soulcode.models.Cliente;
import com.pizzariaSoulcode.Pizzaria_Soulcode.repositores.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ControllerCliente {

    @Autowired
   private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + id));
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente) {
        // Verificar se os dados do cliente são válidos
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Cadastrar o novo cliente no banco de dados
        Cliente novoCliente = clienteRepository.save(cliente);

        // Retornar o URI do recurso recém-criado no cabeçalho Location
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoCliente.getIdCliente())
                .toUri();
        return ResponseEntity.created(uri).body(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        cliente.setIdCliente(id); // Definir o ID do cliente a ser atualizado
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
