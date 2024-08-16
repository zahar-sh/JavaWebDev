package edu.epam.jwd.service.impl;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.service.SortArrayService;

import java.util.Arrays;
import java.util.Comparator;

public class SortArrayServiceImpl implements SortArrayService {
    @Override
    public IntArray sort(IntArray array, Comparator<Integer> comparator) throws CustomException {
        if (array == null) {
            throw new CustomException("array should be not null");
        }
        if (comparator == null) {
            throw new CustomException("comparator should be not null");
        }
        Integer[] boxed = Arrays.stream(array.asJavaArray())
                .boxed()
                .toArray(Integer[]::new);
        Arrays.sort(boxed, comparator);
        int[] result = Arrays.stream(boxed)
                .mapToInt(n -> n)
                .toArray();
        return new IntArray(result);
    }
}
