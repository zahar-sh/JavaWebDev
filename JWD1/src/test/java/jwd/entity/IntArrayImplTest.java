package jwd.entity;

import edu.epam.jwd.entity.IntArray;
import edu.epam.jwd.exception.CustomException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;

public class IntArrayImplTest {
    @Test(groups = "NumberArrayException")
    public void testNumberArrayIsNull() {
        assertThrows(CustomException.class, () -> new IntArray(null));
    }

    @Test(groups = "NumberArrayException")
    public void testNumberArrayGetIllegalIndex() throws CustomException {
        IntArray intArray = new IntArray(new int[]{1, 2, 3});
        assertThrows(CustomException.class, () -> intArray.get(5));
    }

    @Test(groups = "NumberArrayException")
    public void testNumberArraySetIllegalIndex() throws CustomException {
        IntArray intArray = new IntArray(new int[]{1, 2, 3});
        assertThrows(CustomException.class, () -> intArray.set(5, -1));
    }
}
