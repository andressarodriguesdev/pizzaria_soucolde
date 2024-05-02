package com.pizzariaSoulcode.Pizzaria_Soulcode.controllers;

import com.pizzariaSoulcode.Pizzaria_Soulcode.models.Ingrediente;
import com.pizzariaSoulcode.Pizzaria_Soulcode.repositores.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredientes")
public class ControllerIngrediente {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    // Endpoint para adicionar um ingrediente
    @PostMapping("/adicionar")
    public ResponseEntity<Ingrediente> adicionarIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente novoIngrediente = ingredienteRepository.save(ingrediente);
        return new ResponseEntity<>(novoIngrediente, HttpStatus.CREATED);
    }

    // Endpoint para listar todos os ingredientes
    @GetMapping("/listar")
    public ResponseEntity<List<Ingrediente>> listarIngredientes() {
        List<Ingrediente> ingredientes = ingredienteRepository.findAll();
        return new ResponseEntity<>(ingredientes, HttpStatus.OK);
    }

    // Endpoint para buscar um ingrediente pelo ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Ingrediente> buscarIngredientePorId(@PathVariable Long id) {
        Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
        return ingrediente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para deletar um ingrediente pelo ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarIngrediente(@PathVariable Long id) {
        if (ingredienteRepository.existsById(id)) {
            ingredienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para atualizar um ingrediente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Ingrediente> atualizarIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingredienteAtualizado) {
        Optional<Ingrediente> optionalIngrediente = ingredienteRepository.findById(id);
        if (optionalIngrediente.isPresent()) {
            Ingrediente ingrediente = optionalIngrediente.get();
            ingrediente.setNome(ingredienteAtualizado.getNome());
            ingrediente.setPreco(ingredienteAtualizado.getPreco());
            Ingrediente ingredienteAtualizadoDB = ingredienteRepository.save(ingrediente);
            return new ResponseEntity<>(ingredienteAtualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
