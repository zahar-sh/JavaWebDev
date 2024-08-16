package edu.epam.jwd.service;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;

import java.util.Comparator;

public interface SortArrayService {
    IntArray sort(IntArray array, Comparator<Integer> comparator) throws CustomException;
}
