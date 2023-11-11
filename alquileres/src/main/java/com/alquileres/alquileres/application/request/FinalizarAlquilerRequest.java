package com.alquileres.alquileres.application.request;

        import lombok.AccessLevel;
        import lombok.Builder;
        import lombok.Data;
        import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinalizarAlquilerRequest {

    Integer estacionDevolucion;

    String moneda;
}
