package com.estaciones.estaciones.model;

import jakarta.persistence.*;
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

    public Estacion() {
        super();
    }

    public Estacion(Integer aId, String aNombre, LocalDateTime aFechaHoraCreacion, Float aLatitud, Float aLongitud) {
        super();
        id = aId;
        nombre = aNombre;
        fechaHoraCreacion = aFechaHoraCreacion;
        latitud = aLatitud;
        longitud = aLongitud;
    }

    public void update(String nombre, LocalDateTime fechaHoraCreacion, Float latitud, Float longitud) {
        this.nombre = nombre;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
