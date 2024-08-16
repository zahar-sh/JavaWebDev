package edu.epam.jwd.entity;

import edu.epam.jwd.event.ArrayChangeEvent;
import edu.epam.jwd.observer.EventListener;
import edu.epam.jwd.service.CalculationArrayService;
import edu.epam.jwd.service.factory.CalculationArrayServiceFactory;

public class ArrayStatistics implements EventListener<ArrayChangeEvent>, Entity<Integer> {
    private Integer id;
    private IntArray currentArray;
    private int min;
    private int max;
    private int sum;
    private int average;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getSum() {
        return sum;
    }

    public int getAverage() {
        return average;
    }

    public IntArray getCurrentArray() {
        return currentArray;
    }

    public boolean isValidData() {
        return getCurrentArray() != null && getCurrentArray().length() != 0;
    }

    @Override
    public void accept(ArrayChangeEvent event) {
        IntArray array = event.getSource();
        currentArray = array;
        if (isValidData()) {
            CalculationArrayServiceFactory serviceFactory = CalculationArrayServiceFactory.getInstance();
            CalculationArrayService service = serviceFactory.getService();
            min = service.findMin(array).orElse(Integer.MIN_VALUE);
            max = service.findMax(array).orElse(Integer.MIN_VALUE);
            sum = service.sum(array).orElse(Integer.MIN_VALUE);
            average = service.average(array).orElse(Integer.MIN_VALUE);
        }
    }
}
