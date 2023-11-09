package com.alquileres.alquileres.application.response;

import com.alquileres.alquileres.model.Alquiler;
import com.estaciones.estaciones.model.Estacion;
import com.estaciones.estaciones.model.Tarifa;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlquilerResponse {
    Integer id;

    String cliente;

    Integer estado;

    Estacion estacionRetiro;

    Estacion estacionDevolucion;

    LocalDateTime fechaHoraRetiro;

    LocalDateTime fechaHoraDevolucion;

    Float monto;

    Tarifa tarifa;

    public static AlquilerResponse from(Alquiler aAlquiler) {
        return AlquilerResponse.builder()
                .id(aAlquiler.getId())
                .cliente(aAlquiler.getCliente())
                .estado(aAlquiler.getEstado())
                .estacionRetiro(aAlquiler.getEstacionRetiro())
                .estacionDevolucion(aAlquiler.getEstacionDevolucion())
                .fechaHoraRetiro(aAlquiler.getFechaHoraRetiro())
                .fechaHoraDevolucion(aAlquiler.getFechaHoraDevolucion())
                .monto(aAlquiler.getMonto())
                .tarifa(aAlquiler.getTarifa())
                .build();
    }
}
