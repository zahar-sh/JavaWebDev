package edu.epam.jwd.repository;

import edu.epam.jwd.entity.Entity;

public interface Repository<Id, E extends Entity<Id>> extends ReadRepository<Id, E> {
    E add(E entity);

    Iterable<E> addAll(Iterable<E> entities);

    void update(E entity);

    void updateAll(Iterable<E> entities);

    void delete(E entity);

    void deleteAll(Iterable<E> entities);

    void save();
}