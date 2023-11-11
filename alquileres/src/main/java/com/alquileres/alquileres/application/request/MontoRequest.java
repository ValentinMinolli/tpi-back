package com.alquileres.alquileres.application.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MontoRequest {

    String moneda_destino;
    Float importe;

    public MontoRequest(String moneda, Float importe) {
        this.moneda_destino = moneda;
        this.importe = importe;
    }


}
