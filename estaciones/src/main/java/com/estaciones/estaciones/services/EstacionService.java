package com.estaciones.estaciones.services;

import com.estaciones.estaciones.model.Estacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EstacionService {

    List<Estacion> findAll();

    Optional<Estacion> findById(final Integer id);

    Estacion create(final String nombre,
                           final LocalDateTime fechaHoraCreacion,
                           final Float latitud,
                           final Float longitud);

    void delete(final Integer id);

    void update(final Integer id,
                final String nombre,
                final LocalDateTime fechaHoraCreacion,
                final Float latitud,
                final Float longitud);
}
