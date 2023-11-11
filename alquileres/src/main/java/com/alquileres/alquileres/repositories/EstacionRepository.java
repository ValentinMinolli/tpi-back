package com.alquileres.alquileres.repositories;

import com.alquileres.alquileres.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Integer> {
}
