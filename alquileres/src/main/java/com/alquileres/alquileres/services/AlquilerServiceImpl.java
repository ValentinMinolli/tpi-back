package com.alquileres.alquileres.services;

import com.alquileres.alquileres.application.request.MontoRequest;
import com.alquileres.alquileres.application.response.MontoResponse;
import com.alquileres.alquileres.model.Alquiler;
import com.alquileres.alquileres.model.Estacion;
import com.alquileres.alquileres.model.Tarifa;
import com.alquileres.alquileres.repositories.AlquilerRepository;
import com.alquileres.alquileres.repositories.EstacionRepository;
import com.alquileres.alquileres.repositories.IdentifierRepositoryImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AlquilerServiceImpl implements  AlquilerService{
    @Autowired
    AlquilerRepository alquilerRepository;

    @Autowired
    IdentifierRepositoryImpl identifierRepository;

    @Autowired
    EstacionRepository estacionRepository;

    @Autowired
    TarifaServiceImpl tarifaService;

    @Override
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public List<Alquiler> findByEstado() {
        return alquilerRepository.findByEstado(2);
    }

    @Override
    public Optional<Alquiler> findById(final Integer id) {
        return alquilerRepository.findById(id);
    }

    @Override
    public void delete(final Integer id) {
        try {
            alquilerRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    @Override
    @Transactional
    public Alquiler create(final Integer estacionId, final String idCliente) {
        Estacion estacionRetiro = estacionRepository.findById(estacionId).orElseThrow(() -> new IllegalArgumentException("Estacion not found"));
        Integer id = identifierRepository.nextValue("alquileres");
        Alquiler alquiler = new Alquiler(id, estacionRetiro, idCliente);
        return alquilerRepository.save(alquiler);
    }

    @Override
    @Transactional
    public Alquiler finalizarAlquiler(final Integer idAlquiler, final Integer idEstacionDevolucion, final String moneda) {
        Alquiler alquiler = alquilerRepository.findById(idAlquiler).orElseThrow(() -> new IllegalArgumentException("Alquiler not found"));
        LocalDateTime fechaHoraDevolucion = LocalDateTime.now();

        // Buscar la tarifa correspondiente
        int diaMesRetiro = alquiler.getFechaHoraRetiro().getDayOfMonth();
        int mesRetiro = alquiler.getFechaHoraRetiro().getMonthValue();
        int anioRetiro = alquiler.getFechaHoraRetiro().getYear();
        int diaSemana = alquiler.getFechaHoraRetiro().getDayOfWeek().getValue();

        Tarifa tarifaSeleccionada = tarifaService.findByDiaSemana(diaSemana);
        List<Tarifa> listaTarifas = tarifaService.findAll();
        List<Tarifa> tarifasFiltradas = listaTarifas.stream()
                .filter(tarifa -> "C".equals(tarifa.getDefinicion()))
                .toList();

        for (Tarifa tarifa : tarifasFiltradas) {
            if (diaMesRetiro == tarifa.getDiaMes()
                    && mesRetiro == tarifa.getMes()
                    && anioRetiro == tarifa.getAnio()) {
                tarifaSeleccionada = tarifa;
                break;
            }
        }

        // Calcular Monto TOTAL a cobrar

            //Calculo de la distancia entre ambas estaciones
            Estacion estacionDevolucion = estacionRepository.findById(idEstacionDevolucion)
                    .orElseThrow(() -> new IllegalArgumentException("Estacion de devolucion not found"));
            Estacion estacionRetiro = alquiler.getEstacionRetiro();
            double distanciaEntreEstaciones = this.euclideanDistance(estacionDevolucion.getLatitud(), estacionDevolucion.getLongitud(), estacionRetiro.getLatitud(), estacionRetiro.getLongitud()) / 1000 ;
            int cantidadKilimetros = (int) distanciaEntreEstaciones;
            Float montoAdicionalKM = cantidadKilimetros * tarifaSeleccionada.getMontoKm();


            //Calcular el monto por hora y minuto
            Duration duration = Duration.between(alquiler.getFechaHoraRetiro(), fechaHoraDevolucion);
            long horas = duration.toHours();
            long minutosSobrantes = duration.toMinutes() % 60;

            float montoPorHora = horas * tarifaSeleccionada.getMontoHora();

            float montoPorMinutos = 0;
            if (minutosSobrantes < 31) {
                montoPorMinutos = minutosSobrantes * tarifaSeleccionada.getMontoMinutoFraccion();
            } else {
                montoPorMinutos = tarifaSeleccionada.getMontoHora();
            }

            // Calculamos el monto total
            float montoTotal = montoPorHora + montoPorMinutos + tarifaSeleccionada.getMontoFijoAlquiler() + montoAdicionalKM;


        // Calcular montoTotal en la divisa seleccionada si corresponde
        if (moneda != null) {
            montoTotal = this.conversionMoneda(moneda, montoTotal);
        }

        // Actualizar y finalizar el alquiler con los datos correspondientes
        alquiler.update(montoTotal, tarifaSeleccionada, fechaHoraDevolucion, estacionDevolucion);
        return alquilerRepository.save(alquiler);
    }

    private double euclideanDistance(double lat1, double lon1, double lat2, double lon2) {
        // Distancia euclidiana con cada grado correspondiente a 110,000 metros
        double degreesToMeters = 110000.0;

        double dLat = (lat2 - lat1) * degreesToMeters;
        double dLon = (lon2 - lon1) * degreesToMeters;

        return Math.sqrt(dLat * dLat + dLon * dLon);
    }

    private float conversionMoneda(String moneda, float montoTotal) {
            RestTemplate restTemplate = new RestTemplate();

            // Construir el objeto de solicitud para la API externa
            MontoRequest request = new MontoRequest(moneda, montoTotal);

            try {
                // Llamar a la API mandandole el request
                ResponseEntity<MontoResponse> responseEntity = restTemplate.postForEntity("http://34.82.105.125:8080/convertir", request, MontoResponse.class);
                // Obtener la respuesta de la API
                MontoResponse montoResponse = responseEntity.getBody();

                // Actualizar el monto total con la respuesta de la API
                montoTotal = (float) montoResponse.getImporte();

                return montoTotal;
            } catch (Exception e) {
                throw new IllegalArgumentException("Moneda no soportada");
            }
    }
}
