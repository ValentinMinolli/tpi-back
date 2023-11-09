package com.alquileres.alquileres.application.controllers;

import com.alquileres.alquileres.application.ResponseHandler;
import com.alquileres.alquileres.application.response.AlquilerResponse;
import com.alquileres.alquileres.services.AlquilerServiceImpl;
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
}
