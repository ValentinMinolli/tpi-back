package com.alquileres.alquileres.application.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IniciarAlquilerRequest {

    String idCliente;
}
