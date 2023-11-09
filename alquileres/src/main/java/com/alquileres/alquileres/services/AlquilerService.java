package com.alquileres.alquileres.services;

import com.alquileres.alquileres.model.Alquiler;

import java.util.List;
import java.util.Optional;

public interface AlquilerService {

    List<Alquiler> findAll();

    Optional<Alquiler> findById(final Integer id);

    void delete(final Integer id);

}
