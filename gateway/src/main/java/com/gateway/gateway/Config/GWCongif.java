package com.gateway.gateway.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GWCongif {
    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${trabajo-practico.url-microservicio-alquileres}") String uriAlquileres,
                                        @Value("${trabajo-practico.url-microservicio-estaciones}") String uriEstaciones) {
        return builder.routes()
                // Ruteo al Microservicio de Personas
                .route(p -> p.path("/api/alquileres/**").uri(uriAlquileres))
                // Ruteo al Microservicio de Entradas
                .route(p -> p.path("/api/estaciones/**").uri(uriEstaciones))
                .build();

    }
}
