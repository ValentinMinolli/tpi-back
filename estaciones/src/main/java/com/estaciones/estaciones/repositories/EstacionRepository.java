package com.estaciones.estaciones.repositories;

import com.estaciones.estaciones.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Integer> {

}
