package com.pizzariaSoulcode.Pizzaria_Soulcode.controllers;

import com.pizzariaSoulcode.Pizzaria_Soulcode.models.Bebida;
import com.pizzariaSoulcode.Pizzaria_Soulcode.repositores.BebidaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bebidas")
public class ControllerBebida {

    @Autowired
    private BebidaRepository bebidaRepository;

    @GetMapping
    public ResponseEntity<List<Bebida>> listarBebidas() {
        List<Bebida> bebidas = bebidaRepository.findAll();
        return ResponseEntity.ok(bebidas);
    }

    @PostMapping
    public ResponseEntity<Bebida> adicionarBebida(@RequestBody Bebida bebida) {
        if (!bebida.isPrecoValido()) {
            throw new IllegalArgumentException("Preço inválido: deve ser entre R$ 0,00 e R$ 100,00");
        }

        Bebida bebidaSalva = bebidaRepository.save(bebida);
        return ResponseEntity.status(HttpStatus.CREATED).body(bebidaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bebida> atualizarBebida(@PathVariable Long id, @RequestBody Bebida bebidaAtualizada) {
        Bebida bebida = bebidaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bebida não encontrada com o ID: " + id));

        bebida.setNome(bebidaAtualizada.getNome());
        bebida.setPreco(bebidaAtualizada.getPreco());

        bebida = bebidaRepository.save(bebida);
        return ResponseEntity.ok(bebida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarBebida(@PathVariable Long id) {
        bebidaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
