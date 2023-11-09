package com.alquileres.alquileres.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface IdentifierRepository {
    int nextValue(String tableName);
}
