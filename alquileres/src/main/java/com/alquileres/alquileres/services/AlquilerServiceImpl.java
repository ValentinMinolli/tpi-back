package com.alquileres.alquileres.services;

import com.alquileres.alquileres.model.Alquiler;
import com.alquileres.alquileres.model.Estacion;
import com.alquileres.alquileres.repositories.AlquilerRepository;
import com.alquileres.alquileres.repositories.EstacionRepository;
import com.alquileres.alquileres.repositories.IdentifierRepositoryImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlquilerServiceImpl implements  AlquilerService{
    @Autowired
    AlquilerRepository alquilerRepository;

    @Autowired
    IdentifierRepositoryImpl identifierRepository;

    @Autowired
    EstacionRepository estacionRepository;

    @Override
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public Optional<Alquiler> findById(final Integer id) {
        return alquilerRepository.findById(id);
    }

    @Override
    public void delete(final Integer id) {
        try {
            alquilerRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    @Override
    @Transactional
    public Alquiler create(final Integer estacionId) {
        Estacion estacionRetiro = estacionRepository.findById(estacionId).orElseThrow(() -> new IllegalArgumentException("Estacion not found"));
        Integer id = identifierRepository.nextValue("alquileres");
        Alquiler alquiler = new Alquiler(id, estacionRetiro);
        return alquilerRepository.save(alquiler);
    }

}
