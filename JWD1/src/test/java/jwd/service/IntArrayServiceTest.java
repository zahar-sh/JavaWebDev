package jwd.service;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;
import edu.epam.jwd.service.IntArrayService;
import edu.epam.jwd.service.impl.IntArrayServiceImpl;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

public class IntArrayServiceTest {

    @Test(groups = "CalculationArrayException")
    public void testReplacing() throws CustomException {
        IntArrayService service = new IntArrayServiceImpl();
        IntArray original = new IntArray(new int[]{1, -2, 3, 4, -5, 6});
        IntArray replaced = service.replaceIf(original, (n) -> n < 0, 12);
        assertTrue(Arrays.equals(replaced.asJavaArray(), new int[]{1, 12, 3, 4, 12, 6}));
    }
}