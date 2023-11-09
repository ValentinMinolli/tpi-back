package com.estaciones.estaciones.application.controllers;

import com.estaciones.estaciones.application.ResponseHandler;
import com.estaciones.estaciones.application.request.CreateEstacionRequest;
import com.estaciones.estaciones.application.request.UpdateEstacionRequest;
import com.estaciones.estaciones.application.response.EstacionResponse;
import com.estaciones.estaciones.model.Estacion;
import com.estaciones.estaciones.services.EstacionServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/estaciones")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionController {

    EstacionServiceImpl estacionService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<EstacionResponse> alquileres = estacionService.findAll()
                    .stream()
                    .map(EstacionResponse::from)
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
            return estacionService.findById(id)
                    .map(aEstacion -> ResponseHandler.success(EstacionResponse.from(aEstacion)))
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
            estacionService.delete(id);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            //Ya fue borrado, asiq devuelvo 204
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UpdateEstacionRequest aRequest) {
        try {
            estacionService.update(id,
                    aRequest.getNombre(),
                    aRequest.getFechaHoraCreacion(),
                    aRequest.getLatitud(),
                    aRequest.getLongitud());
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateEstacionRequest aRequest) {
        try {
            Estacion estacion = estacionService.create(
                    aRequest.getNombre(),
                    aRequest.getFechaHoraCreacion(),
                    aRequest.getLatitud(),
                    aRequest.getLongitud());

            return ResponseHandler.success(EstacionResponse.from(estacion));
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}