package com.alquileres.alquileres.application.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MontoResponse {

    String moneda;
    Float importe;
}
