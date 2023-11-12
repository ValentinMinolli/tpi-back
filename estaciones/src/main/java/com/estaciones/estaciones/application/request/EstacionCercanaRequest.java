package com.estaciones.estaciones.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EstacionCercanaRequest {
    @NotNull(message = "La latitud no puede ser nula")
    Float latitud;

    @NotNull(message = "La longitud no puede ser nula")
    Float longitud;
}
