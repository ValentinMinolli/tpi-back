package com.estaciones.estaciones.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateEstacionRequest {

    @NotBlank(message = "El nombre no puede estar en blanco")
    String nombre;

    @NotNull(message = "La fecha y hora de creación no puede ser nula")
    LocalDateTime fechaHoraCreacion;

    @NotNull(message = "La latitud no puede ser nula")
    Float latitud;

    @NotNull(message = "La longitud no puede ser nula")
    Float longitud;
}
