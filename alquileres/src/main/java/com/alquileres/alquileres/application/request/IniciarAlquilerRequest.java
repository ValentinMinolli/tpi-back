package com.alquileres.alquileres.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IniciarAlquilerRequest {
    @NotNull(message = "El idCliente no puede ser nulo")
    String idCliente;
}
