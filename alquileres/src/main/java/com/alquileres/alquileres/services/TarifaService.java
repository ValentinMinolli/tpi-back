package com.alquileres.alquileres.services;



import com.alquileres.alquileres.model.Tarifa;

import java.util.List;
import java.util.Optional;

public interface TarifaService {

    List<Tarifa> findAll();

    Optional<Tarifa> findById(final Integer id);

    void delete(final Integer id);

    Tarifa findByDiaSemana(int diaSemana);
}
