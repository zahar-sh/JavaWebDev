package edu.epam.jwd.repository.impl;

import edu.epam.jwd.entity.Entity;
import edu.epam.jwd.repository.Repository;
import edu.epam.jwd.repository.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class RepositoryBase<Id, E extends Entity<Id>> implements Repository<Id, E> {

    protected Stream<E> applySpecification(Stream<E> stream, Specification<E> specification) {
        List<Predicate<E>> predicates = specification.getPredicates();
        if (predicates != null && !predicates.isEmpty()) {
            stream = stream.filter(e -> predicates.stream()
                    .allMatch(predicate -> predicate.test(e)));
        }
        Comparator<E> comparator = specification.getComparator();
        if (comparator != null) {
            stream = stream.sorted(comparator);
        }

        long offset = specification.getOffset();
        if (offset > 0) {
            stream = stream.skip(offset);
        }

        int limit = specification.getLimit();
        if (limit > 0) {
            stream = stream.limit(limit);
        }
        return stream;
    }

    @Override
    public boolean any() {
        return count() != 0;
    }

    @Override
    public boolean any(Specification<E> specification) {
        return count(specification) != 0;
    }

    @Override
    public E findById(Id id) {
        SpecificationImpl<E> spec = new SpecificationImpl<>();
        spec.getPredicates().add(e -> id.equals(e.getId()));
        return findBy(spec);
    }

    @Override
    public List<E> addAll(Iterable<E> entities) {
        List<E> list = new ArrayList<>();
        entities.forEach(list::add);
        return list;
    }

    @Override
    public void updateAll(Iterable<E> entities) {
        entities.forEach(this::update);
    }

    @Override
    public void deleteAll(Iterable<E> entities) {
        entities.forEach(this::delete);
    }
}
