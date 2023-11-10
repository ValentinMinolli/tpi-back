package com.alquileres.alquileres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
@Entity
@Table(name = "tarifas")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tarifa {

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "tipo_tarifa")
    Integer tipoTarifa;

    @Column(name = "definicion")
    String definicion;

    @Column(name = "dia_semana")
    Integer diaSemana;

    @Column(name = "dia_mes")
    Integer diaMes;

    @Column(name = "mes")
    Integer mes;

    @Column(name = "anio")
    Integer anio;
    @Column(name = "monto_fijo_alquiler")
    Float montoFijoAlquiler;
    @Column(name = "monto_minuto_fraccion")
    Float montoMinutoFraccion;

    @Column(name = "monto_km")
    Float montoKm;

    @Column(name = "monto_hora")
    Float montoHora;
}
