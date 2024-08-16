package edu.epam.jwd.service.impl;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.service.IntArrayService;

import java.util.Arrays;
import java.util.function.Predicate;

public class IntArrayServiceImpl implements IntArrayService {

    public IntArray replaceIf(IntArray array, Predicate<Integer> predicate, Integer replacement) throws CustomException {
        if (array == null) {
            throw new CustomException("array should be not null");
        }
        int[] numbers = array.asJavaArray();
        int[] mapped = Arrays.stream(numbers)
                .map(number -> predicate.test(number) ? replacement : number)
                .toArray();
        return new IntArray(mapped);
    }
}
