package com.estaciones.estaciones.services;

import com.estaciones.estaciones.model.Tarifa;

import java.util.List;
import java.util.Optional;

public interface TarifaService {

    List<Tarifa> findAll();

    Optional<Tarifa> findById(final Integer id);

    void delete(final Integer id);
}
