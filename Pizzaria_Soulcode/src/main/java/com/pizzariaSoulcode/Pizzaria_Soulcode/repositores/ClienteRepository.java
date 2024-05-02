package com.pizzariaSoulcode.Pizzaria_Soulcode.repositores;

import com.pizzariaSoulcode.Pizzaria_Soulcode.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente , Long> {
}
