package edu.epam.jwd.repository;

import edu.epam.jwd.entity.IntArray;

import java.util.List;

public interface IntArrayRepository extends ObservableRepository<Integer, IntArray> {
    List<IntArray> findArraysWhenAverageGreaterThat(int average);

    List<IntArray> findArraysWhenSumLessThat(int sum);

    List<IntArray> findArraysWhenMaxLessThat(int max);

    IntArray findFirstArrayWhenMaxFirstElement();

    List<IntArray> findFirst3ArraysSortedByLength();
}
