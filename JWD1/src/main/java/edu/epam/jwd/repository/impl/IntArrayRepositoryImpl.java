package edu.epam.jwd.repository.impl;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.repository.IntArrayRepository;
import edu.epam.jwd.repository.Specification;
import edu.epam.jwd.service.CalculationArrayService;
import edu.epam.jwd.service.IntArrayService;
import edu.epam.jwd.service.factory.CalculationArrayServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class IntArrayRepositoryImpl extends ObservableMapRepository<Integer, IntArray> implements IntArrayRepository {
    private static final IntArrayRepositoryImpl INSTANCE;

    static {
        try {
            INSTANCE = new IntArrayRepositoryImpl();
        } catch (CustomException e) {
            Logger logger = LogManager.getLogger();
            logger.log(Level.ERROR, e);
            throw new Error(e);
        }
    }

    public static IntArrayRepositoryImpl getInstance() {
        return INSTANCE;
    }

    private final CalculationArrayService service;

    private IntArrayRepositoryImpl() throws CustomException {
        super(new Supplier<Integer>() {
            private int seed = 0;

            @Override
            public Integer get() {
                return ++seed;
            }
        });
        this.service = CalculationArrayServiceFactory.getInstance().getService();
    }

    @Override
    public List<IntArray> findArraysWhenAverageGreaterThat(int average) {
        SpecificationImpl<IntArray> specification = new SpecificationImpl<>();
        Predicate<IntArray> predicate = intArray -> {
            OptionalInt optional = service.average(intArray);
            return optional.isPresent() && optional.getAsInt() > average;
        };
        specification.getPredicates().add(predicate);
        return findAllBy(specification);
    }

    @Override
    public List<IntArray> findArraysWhenSumLessThat(int sum) {
        SpecificationImpl<IntArray> specification = new SpecificationImpl<>();
        Predicate<IntArray> predicate = intArray -> {
            OptionalInt optional = service.sum(intArray);
            return optional.isPresent() && optional.getAsInt() < sum;
        };
        specification.getPredicates().add(predicate);
        return findAllBy(specification);
    }

    @Override
    public List<IntArray> findArraysWhenMaxLessThat(int max) {
        SpecificationImpl<IntArray> specification = new SpecificationImpl<>();
        Predicate<IntArray> predicate = intArray -> {
            OptionalInt optional = service.findMax(intArray);
            return optional.isPresent() && optional.getAsInt() < max;
        };
        specification.getPredicates().add(predicate);
        return findAllBy(specification);
    }

    @Override
    public IntArray findFirstArrayWhenMaxFirstElement() {
        Specification<IntArray> specification = new SpecificationImpl<>();
        specification.setComparator(IntArrayService.BY_FIRST_ELEMENT);
        return findBy(specification);
    }

    @Override
    public List<IntArray> findFirst3ArraysSortedByLength() {
        Specification<IntArray> specification = new SpecificationImpl<>();
        specification.setComparator(IntArrayService.BY_LENGTH);
        specification.setLimit(3);
        return findAllBy(specification);
    }
}
