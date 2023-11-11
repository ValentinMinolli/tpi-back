package com.alquileres.alquileres.repositories;

import com.alquileres.alquileres.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {
    Tarifa findByDiaSemana(int diaSemana);
}
