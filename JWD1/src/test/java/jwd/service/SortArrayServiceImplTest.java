package jwd.service;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.service.IntArrayService;
import edu.epam.jwd.service.impl.SortArrayServiceImpl;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

public class SortArrayServiceImplTest {
    @Test(groups = "Sort")
    public void testSortIfArrayNull() {
        SortArrayServiceImpl service = new SortArrayServiceImpl();
        assertThrows(CustomException.class, () -> service.sort(null, (n1, n2) -> 0));
    }

    @Test(groups = "Sort")
    public void testSortIfComparatorNull() {
        SortArrayServiceImpl service = new SortArrayServiceImpl();
        assertThrows(CustomException.class, () -> service.sort(new IntArray(new int[1]), null));
    }

    @Test(groups = "Sort")
    public void testSort() throws CustomException {
        SortArrayServiceImpl service = new SortArrayServiceImpl();
        int[] numbers = {-3, 1, -2, 3, 14, 5};
        IntArray sortedArray = service.sort(new IntArray(numbers.clone()), IntArrayService.DEFAULT_COMPARATOR);
        Arrays.sort(numbers);
        int[] javaArray = sortedArray.asJavaArray();
        assertTrue(Arrays.equals(javaArray, numbers));
    }
}
