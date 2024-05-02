package com.pizzariaSoulcode.Pizzaria_Soulcode.repositores;

import com.pizzariaSoulcode.Pizzaria_Soulcode.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza,  Long> {
}

