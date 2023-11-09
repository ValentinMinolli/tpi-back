package com.alquileres.alquileres.services;

import com.alquileres.alquileres.model.Alquiler;
import com.alquileres.alquileres.repositories.AlquilerRepository;
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
public class AlquilerServiceImpl implements  AlquilerService{
    @Autowired
    AlquilerRepository alquilerRepository;

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
}
