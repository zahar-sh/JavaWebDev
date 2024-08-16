package edu.epam.jwd.repository;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface Specification<E> {
    List<Predicate<E>> getPredicates();

    Comparator<E> getComparator();

    void setComparator(Comparator<E> comparator);

    long getOffset();

    void setOffset(long offset);

    int getLimit();

    void setLimit(int limit);
}