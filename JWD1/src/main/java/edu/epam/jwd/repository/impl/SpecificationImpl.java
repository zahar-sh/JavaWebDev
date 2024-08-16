package edu.epam.jwd.repository.impl;

import edu.epam.jwd.repository.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class SpecificationImpl<E> implements Specification<E> {
    private final List<Predicate<E>> predicates;
    private Comparator<E> comparator;
    private long offset;
    private int limit;

    public SpecificationImpl() {
        predicates = new ArrayList<>();
    }

    @Override
    public List<Predicate<E>> getPredicates() {
        return predicates;
    }

    @Override
    public Comparator<E> getComparator() {
        return comparator;
    }

    @Override
    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public void setOffset(long offset) {
        this.offset = offset;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificationImpl<?> that = (SpecificationImpl<?>) o;
        return offset == that.offset &&
                limit == that.limit &&
                Objects.equals(predicates, that.predicates) &&
                Objects.equals(comparator, that.comparator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predicates, comparator, offset, limit);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("SpecificationImpl{")
                .append("predicates=").append(predicates)
                .append(", comparator=").append(comparator)
                .append(", offset=").append(offset)
                .append(", limit=").append(limit)
                .append('}')
                .toString();
    }
}
