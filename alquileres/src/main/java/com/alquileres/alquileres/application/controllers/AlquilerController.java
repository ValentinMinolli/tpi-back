package com.alquileres.alquileres.application.controllers;

import com.alquileres.alquileres.application.ResponseHandler;
import com.alquileres.alquileres.application.request.FinalizarAlquilerRequest;
import com.alquileres.alquileres.application.request.IniciarAlquilerRequest;
import com.alquileres.alquileres.application.response.AlquilerResponse;
import com.alquileres.alquileres.model.Alquiler;
import com.alquileres.alquileres.services.AlquilerServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/alquileres")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlquilerController {

    AlquilerServiceImpl alquilerService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<AlquilerResponse> alquileres = alquilerService.findAll()
                    .stream()
                    .map(AlquilerResponse::from)
                    .toList();

            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    //Obtener un listado de los alquileres realizados aplicando, por lo menos, un filtro(Estado = 2)
    @GetMapping("/finalizados")
    public ResponseEntity<Object> encontrarAlquileresFinalizados() {
        try {
            List<AlquilerResponse> alquileres = alquilerService.findByEstado()
                    .stream()
                    .map(AlquilerResponse::from)
                    .toList();

            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            return alquilerService.findById(id)
                    .map(aAlquiler -> ResponseHandler.success(AlquilerResponse.from(aAlquiler)))
                    .orElseGet(ResponseHandler::notFound);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            alquilerService.delete(id);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            //Ya fue borrado, asiq devuelvo 204
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    //Iniciar el alquiler de una bicicleta desde una estación dada
    @PostMapping("/iniciarAlquiler/{idEstacion}")
    public ResponseEntity<Object> iniciarAlquiler(@PathVariable Integer idEstacion, @Valid @RequestBody IniciarAlquilerRequest aAlquiler) {
        try {
            Alquiler alquiler = alquilerService.create(idEstacion, aAlquiler.getIdCliente());
            return ResponseHandler.success(AlquilerResponse.from(alquiler));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    //Finalizar un alquiler en curso, informando los datos del mismo y el costo expresado en la moneda que el cliente desee.
    @PatchMapping("/finalizarAlquiler/{idAlquiler}")
    public ResponseEntity<Object> finalizarAlquiler(@PathVariable Integer idAlquiler, @Valid @RequestBody FinalizarAlquilerRequest aAlquiler) {
        try {
            Alquiler alquiler = alquilerService.finalizarAlquiler(idAlquiler, aAlquiler.getEstacionDevolucion(), aAlquiler.getMoneda());
            return ResponseHandler.success(AlquilerResponse.from(alquiler));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
