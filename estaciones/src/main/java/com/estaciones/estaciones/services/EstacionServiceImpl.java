package com.estaciones.estaciones.services;

import com.estaciones.estaciones.model.Estacion;
import com.estaciones.estaciones.repositories.EstacionRepository;
import com.estaciones.estaciones.repositories.IdentifierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionServiceImpl implements  EstacionService{
    @Autowired
    EstacionRepository estacionRepository;

    @Autowired
    IdentifierRepository identifierRepositoryImpl;

    @Override
    public List<Estacion> findAll() {
        return estacionRepository.findAll();
    }

    @Override
    public Optional<Estacion> findById(final Integer id) {
        return estacionRepository.findById(id);
    }

    @Override
    public void delete(final Integer id) {
        try {
            estacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    @Override
    @Transactional
    public Estacion create(final String nombre,
                           final LocalDateTime fechaHoraCreacion,
                           final Float latitud,
                           final Float longitud) {
        Integer id = identifierRepositoryImpl.nextValue("estaciones");
        Estacion estacion = new Estacion(id, nombre, fechaHoraCreacion, latitud, longitud);
        return estacionRepository.save(estacion);
    }
    @Override
    @Transactional
    public void update(final Integer id,
                       final String nombre,
                       final LocalDateTime fechaHoraCreacion,
                       final Float latitud,
                       final Float longitud) {
        Estacion estacion = estacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estacion not found"));
        estacion.update(nombre, fechaHoraCreacion, latitud, longitud);
        estacionRepository.save(estacion);
    }
}