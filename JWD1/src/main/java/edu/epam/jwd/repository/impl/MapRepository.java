package edu.epam.jwd.repository.impl;

import edu.epam.jwd.entity.Entity;
import edu.epam.jwd.repository.Specification;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapRepository<Id, E extends Entity<Id>> extends RepositoryBase<Id, E> {
    private final Supplier<Id> idGenerator;
    private final Map<Id, E> entities;

    public MapRepository(Supplier<Id> idGenerator) {
        this.idGenerator = idGenerator;
        this.entities = new HashMap<>();
    }

    @Override
    public long count() {
        return entities.size();
    }

    @Override
    public long count(Specification<E> specification) {
        Stream<E> stream = applySpecification(entities.values().stream(), specification);
        return stream.count();
    }

    @Override
    public E findBy(Specification<E> specification) {
        Stream<E> stream = applySpecification(entities.values().stream(), specification);
        Optional<E> optional = stream.findAny();
        return optional.orElse(null);
    }

    @Override
    public List<E> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public List<E> findAllBy(Specification<E> specification) {
        Stream<E> stream = applySpecification(entities.values().stream(), specification);
        return stream.collect(Collectors.toList());
    }

    @Override
    public E add(E entity) {
        Id id = idGenerator.get();
        entity.setId(id);
        entities.put(id, entity);
        return entity;
    }

    @Override
    public void update(E entity) {
        if (entity.getId() == null) {
            add(entity);
        } else {
            entities.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(E entity) {
        entities.remove(entity.getId());
    }

    @Override
    public void save() {
    }
}
