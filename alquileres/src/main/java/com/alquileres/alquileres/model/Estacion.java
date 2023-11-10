package com.alquileres.alquileres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "estaciones")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Estacion {

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "fecha_hora_creacion")
    LocalDateTime fechaHoraCreacion;

    @Column(name = "latitud")
    Float latitud;

    @Column(name = "longitud")
    Float longitud;
}
