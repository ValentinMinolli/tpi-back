package com.estaciones.estaciones.application.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateEstacionRequest {

    String nombre;


    LocalDateTime fechaHoraCreacion;


    Float latitud;


    Float longitud;
}
