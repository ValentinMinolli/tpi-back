package com.alquileres.alquileres.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Table(name = "alquileres")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Alquiler {

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "id_cliente")
    String cliente;

    @Column(name = "estado")
    Integer estado;

    @OneToOne
    @JoinColumn(name = "estacion_retiro")
    Estacion estacionRetiro;

    @OneToOne
    @JoinColumn(name = "estacion_devolucion")
    Estacion estacionDevolucion;

    @Column(name = "fecha_hora_retiro")
    LocalDateTime fechaHoraRetiro;

    @Column(name = "fecha_hora_devolucion")
    LocalDateTime fechaHoraDevolucion;

    @Column(name = "monto")
    Float monto;

    @OneToOne
    @JoinColumn(name = "id_tarifa")
    Tarifa tarifa;

    public Alquiler(Integer aId, Estacion aEstacionRetiro) {
        id = aId;
        cliente = "a1";
        estado = 1;
        estacionRetiro = aEstacionRetiro;
        estacionDevolucion = null;
        fechaHoraRetiro = LocalDateTime.now();
        fechaHoraDevolucion = null;
        monto = null;
        tarifa = null;
    }
}
