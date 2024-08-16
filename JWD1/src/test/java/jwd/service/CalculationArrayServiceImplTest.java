package jwd.service;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.service.impl.CalculationArrayServiceImpl;
import org.testng.annotations.Test;

import java.util.OptionalInt;

import static org.testng.Assert.*;

public class CalculationArrayServiceImplTest {

    @Test(groups = "CalculationArrayException")
    public void testFindMinIfArrayEmpty() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        OptionalInt min = service.findMin(new IntArray(new int[0]));
        assertFalse(min.isPresent());
    }

    @Test(groups = "CalculationArrayException")
    public void testFindMaxIfArrayEmpty() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        OptionalInt max = service.findMax(new IntArray(new int[0]));
        assertFalse(max.isPresent());
    }

    @Test(groups = "CalculationArrayException")
    public void testSumIfArrayEmpty() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        OptionalInt sum = service.sum(new IntArray(new int[0]));
        assertFalse(sum.isPresent());
    }

    @Test(groups = "CalculationArrayException")
    public void testAverageIfArrayEmpty() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        OptionalInt average = service.average(new IntArray(new int[0]));
        assertFalse(average.isPresent());
    }

    ///
    @Test(groups = "CalculationArrayException")
    public void testFindMin() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        OptionalInt min = service.findMin(new IntArray(new int[]{1, 2, 3}));
        assertTrue(min.isPresent());
        assertEquals(min.getAsInt(), 1);
    }

    @Test(groups = "CalculationArrayException")
    public void testFindMax() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        OptionalInt max = service.findMax(new IntArray(new int[]{1, 2, 3}));
        assertTrue(max.isPresent());
        assertEquals(max.getAsInt(), 3.0);
    }

    @Test(groups = "CalculationArrayException")
    public void testSum() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        OptionalInt sum = service.sum(new IntArray(new int[]{1, 2, 3}));
        assertTrue(sum.isPresent());
        assertEquals(sum.getAsInt(), 6.0);
    }

    @Test(groups = "CalculationArrayException")
    public void testAverage() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        OptionalInt average = service.average(new IntArray(new int[]{1, 2, 3}));
        assertTrue(average.isPresent());
        assertEquals(average.getAsInt(), 2.0);
    }

    @Test(groups = "CalculationArrayException")
    public void testCountPositive() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        long count = service.countPositive(new IntArray(new int[]{1, -2, -1, 2, -3, 3}));
        assertEquals(count, 3.0);
    }

    @Test(groups = "CalculationArrayException")
    public void testCountNegative() throws CustomException {
        CalculationArrayServiceImpl service = new CalculationArrayServiceImpl();
        long count = service.countNegative(new IntArray(new int[]{1, -2, -1, 2, -3, 3}));
        assertEquals(count, 3.0);
    }
}
