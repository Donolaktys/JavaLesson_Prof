package Task1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class newArrayTest {
    private MainClass mainClass;

    @Before
    public void init() {
        mainClass = new MainClass();
    }

    @Test
    public void Test1() {
        int[] array = {3, 4, 5, 6};
        int[] newArr = {5, 6};
        Assert.assertArrayEquals(newArr, MainClass.newArray(array));
    }

    @Test
    public void Test2() {
        int[] array = {3, 4, 5, 6, 7, 4, 3, 8};
        int[] newArr = {3, 8};
        Assert.assertArrayEquals(newArr, MainClass.newArray(array));
    }

    @Test (expected = RuntimeException.class)
    public void TestException() {
        int[] array = {3, 5, 6, 7, 3, 8};
        MainClass.newArray(array);
    }
}
