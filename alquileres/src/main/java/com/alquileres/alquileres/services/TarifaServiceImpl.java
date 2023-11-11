package com.alquileres.alquileres.services;


import com.alquileres.alquileres.model.Tarifa;
import com.alquileres.alquileres.repositories.IdentifierRepository;
import com.alquileres.alquileres.repositories.TarifaRepository;
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

    @Override
    public List<Tarifa> findAll() {
        return tarifaRepository.findAll();
    }

    @Override
    public Optional<Tarifa> findById(final Integer id) {
        return tarifaRepository.findById(id);
    }

    @Override
    public void delete(final Integer id) {
        try {
            tarifaRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    @Override
    public Tarifa findByDiaSemana(int diaSemana) {
        return tarifaRepository.findByDiaSemana(diaSemana);
    }
}
