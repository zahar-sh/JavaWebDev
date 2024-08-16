package edu.epam.jwd.repository.impl;

import edu.epam.jwd.entity.Entity;
import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.repository.Repository;
import edu.epam.jwd.repository.Specification;

import java.util.List;

public class RepositoryWrapper<Id, E extends Entity<Id>> implements Repository<Id, E> {
    private Repository<Id, E> innerRepository;

    public RepositoryWrapper(Repository<Id, E> innerRepository) throws CustomException {
        setInnerRepository(innerRepository);
    }

    public Repository<Id, E> getInnerRepository() {
        return innerRepository;
    }

    public void setInnerRepository(Repository<Id, E> innerRepository) throws CustomException {
        if (innerRepository == null) {
            throw new CustomException("innerRepository");
        }
        this.innerRepository = innerRepository;
    }

    @Override
    public long count() {
        return innerRepository.count();
    }

    @Override
    public long count(Specification<E> specification) {
        return innerRepository.count(specification);
    }

    @Override
    public boolean any() {
        return innerRepository.any();
    }

    @Override
    public boolean any(Specification<E> specification) {
        return innerRepository.any(specification);
    }

    @Override
    public E findById(Id id) {
        return innerRepository.findById(id);
    }

    @Override
    public E findBy(Specification<E> specification) {
        return innerRepository.findBy(specification);
    }

    @Override
    public List<E> findAll() {
        return innerRepository.findAll();
    }

    @Override
    public List<E> findAllBy(Specification<E> specification) {
        return innerRepository.findAllBy(specification);
    }

    @Override
    public E add(E entity) {
        return innerRepository.add(entity);
    }

    @Override
    public Iterable<E> addAll(Iterable<E> entities) {
        return innerRepository.addAll(entities);
    }

    @Override
    public void update(E entity) {
        innerRepository.update(entity);
    }

    @Override
    public void updateAll(Iterable<E> entities) {
        innerRepository.updateAll(entities);
    }

    @Override
    public void delete(E entity) {
        innerRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<E> entities) {
        innerRepository.deleteAll(entities);
    }

    @Override
    public void save() {
        innerRepository.save();
    }
}
