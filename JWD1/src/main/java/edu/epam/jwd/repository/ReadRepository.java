package edu.epam.jwd.repository;

import edu.epam.jwd.entity.Entity;

import java.util.List;

public interface ReadRepository<Id, E extends Entity<Id>> {
    long count();

    long count(Specification<E> specification);

    boolean any();

    boolean any(Specification<E> specification);

    E findById(Id id);

    E findBy(Specification<E> specification);

    List<E> findAll();

    List<E> findAllBy(Specification<E> specification);
}