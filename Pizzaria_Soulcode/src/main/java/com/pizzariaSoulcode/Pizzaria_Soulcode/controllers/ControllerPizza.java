package com.pizzariaSoulcode.Pizzaria_Soulcode.controllers;

import com.pizzariaSoulcode.Pizzaria_Soulcode.models.Pizza;
import com.pizzariaSoulcode.Pizzaria_Soulcode.repositores.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class ControllerPizza {

    private PizzaRepository pizzaRepository;

    @Autowired
    public ControllerPizza(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    // Endpoint para adicionar uma nova pizza
    @PostMapping
    public ResponseEntity<Pizza> adicionarPizza(@RequestBody Pizza pizza) {
        Pizza novaPizza = pizzaRepository.save(pizza);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPizza);
    }

    // Endpoint para buscar todas as pizzas
    @GetMapping
    public ResponseEntity<List<Pizza>> listarPizzas() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return ResponseEntity.ok(pizzas);
    }

    // Endpoint para buscar uma pizza pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> buscarPizzaPorId(@PathVariable Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        if (pizza != null) {
            return ResponseEntity.ok(pizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para atualizar uma pizza
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> atualizarPizza(@PathVariable Long id, @RequestBody Pizza pizzaAtualizada) {
        if (pizzaRepository.existsById(id)) {
            pizzaAtualizada.setIdPizza(id);
            Pizza pizza = pizzaRepository.save(pizzaAtualizada);
            return ResponseEntity.ok(pizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para deletar uma pizza
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPizza(@PathVariable Long id) {
        if (pizzaRepository.existsById(id)) {
            pizzaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
