package edu.epam.jwd.service.impl;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.service.CalculationArrayService;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class CalculationArrayServiceImpl implements CalculationArrayService {

    private IntStream streamFrom(IntArray array) {
        return Arrays.stream(array.asJavaArray());
    }

    @Override
    public OptionalInt findMin(IntArray array) {
        return array.length() == 0 ?
                OptionalInt.empty() :
                streamFrom(array).min();
    }

    @Override
    public OptionalInt findMax(IntArray array) {
        return array.length() == 0 ?
                OptionalInt.empty() :
                streamFrom(array).max();
    }

    @Override
    public OptionalInt sum(IntArray array) {
       return array.length() == 0 ?
                OptionalInt.empty() :
                OptionalInt.of(streamFrom(array).sum());
    }

    @Override
    public OptionalInt average(IntArray array) {
        return array.length() == 0 ?
                OptionalInt.empty() :
                OptionalInt.of(streamFrom(array).sum() / array.length());
    }

    @Override
    public long countPositive(IntArray array) {
        IntStream stream = streamFrom(array);
        return stream.filter(number -> number > 0).count();
    }

    @Override
    public long countNegative(IntArray array) {
        IntStream stream = streamFrom(array);
        return stream.filter(number -> number < 0).count();
    }
}
