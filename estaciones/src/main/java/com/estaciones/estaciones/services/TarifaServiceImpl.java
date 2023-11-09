package com.estaciones.estaciones.services;

import com.estaciones.estaciones.model.Estacion;
import com.estaciones.estaciones.model.Tarifa;
import com.estaciones.estaciones.repositories.IdentifierRepository;
import com.estaciones.estaciones.repositories.TarifaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TarifaServiceImpl implements TarifaService{
    @Autowired
    TarifaRepository tarifaRepository;

    @Autowired
    IdentifierRepository identifierRepositoryImpl;

    public List<Tarifa> findAll() {
        return tarifaRepository.findAll();
    }

    public Optional<Tarifa> findById(final Integer id) {
        return tarifaRepository.findById(id);
    }

    public void delete(final Integer id) {
        try {
            tarifaRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Customer not found");
        }
    }
}
