package edu.epam.jwd.service;

import edu.epam.jwd.entity.IntArray;

import java.util.OptionalInt;


public interface CalculationArrayService {
    OptionalInt findMin(IntArray array);

    OptionalInt findMax(IntArray array);

    OptionalInt sum(IntArray array);

    OptionalInt average(IntArray array);

    long countPositive(IntArray array);

    long countNegative(IntArray array);
}
