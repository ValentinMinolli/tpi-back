package com.estaciones.estaciones.application.response;

import com.estaciones.estaciones.model.Estacion;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EstacionResponse {

    Integer id;


    String nombre;


    LocalDateTime fechaHoraCreacion;


    Float latitud;


    Float longitud;

    public static EstacionResponse from(Estacion aEstacion) {
        return EstacionResponse.builder()
                .id(aEstacion.getId())
                .nombre(aEstacion.getNombre())
                .fechaHoraCreacion(aEstacion.getFechaHoraCreacion())
                .latitud(aEstacion.getLatitud())
                .longitud(aEstacion.getLongitud())
                .build();
    }
}
