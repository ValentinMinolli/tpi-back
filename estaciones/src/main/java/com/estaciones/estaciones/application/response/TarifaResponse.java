package com.estaciones.estaciones.application.response;

import com.estaciones.estaciones.model.Tarifa;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TarifaResponse {
    Integer id;

    Integer tipoTarifa;

    String definicion;

    Integer diaSemana;

    Integer diaMes;

    Integer mes;
    Integer anio;
    Float montoFijoAlquiler;
    Float montoMinutoFraccion;

    Float montoKm;

    Float montoHora;

    public static TarifaResponse from(Tarifa aTarifa) {
        return TarifaResponse.builder()
                .id(aTarifa.getId())
                .tipoTarifa(aTarifa.getTipoTarifa())
                .definicion(aTarifa.getDefinicion())
                .diaSemana(aTarifa.getDiaSemana())
                .diaMes(aTarifa.getDiaMes())
                .mes(aTarifa.getMes())
                .anio(aTarifa.getAnio())
                .montoFijoAlquiler(aTarifa.getMontoFijoAlquiler())
                .montoMinutoFraccion(aTarifa.getMontoMinutoFraccion())
                .montoKm(aTarifa.getMontoKm())
                .montoHora(aTarifa.getMontoHora())
                .build();
    }
}
