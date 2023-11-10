package com.estaciones.estaciones.application.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EstacionCercanaRequest {
    Float latitud;


    Float longitud;
}
