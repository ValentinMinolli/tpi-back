package com.alquileres.alquileres.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinalizarAlquilerRequest {
    @NotNull(message = "La estacionDevolucion no puede ser nula")
    Integer estacionDevolucion;

    String moneda;
}
