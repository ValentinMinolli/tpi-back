package com.alquileres.alquileres.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IdentifierRepositoryImpl implements IdentifierRepository {

    @PersistenceContext
    EntityManager entityManager;

    public int nextValue(final String tableName) {

        Integer result = (Integer) entityManager.createNativeQuery(format("SELECT count(*) FROM %s;", tableName)).getSingleResult();

        return result + 1;
    }
}