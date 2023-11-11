package com.alquileres.alquileres.application.controllers;


import com.alquileres.alquileres.application.ResponseHandler;
import com.alquileres.alquileres.application.response.TarifaResponse;
import com.alquileres.alquileres.services.TarifaServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/tarifas")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TarifaController {

    TarifaServiceImpl tarifaService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<TarifaResponse> alquileres = tarifaService.findAll()
                    .stream()
                    .map(TarifaResponse::from)
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
            return tarifaService.findById(id)
                    .map(aTarifa -> ResponseHandler.success(TarifaResponse.from(aTarifa)))
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
            tarifaService.delete(id);
            return ResponseHandler.noContent();
        } catch (IllegalArgumentException e) {
            //Ya fue borrado, asiq devuelvo 204
            return ResponseHandler.noContent();
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
