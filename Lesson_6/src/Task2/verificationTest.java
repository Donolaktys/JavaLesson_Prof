package Task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class verificationTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {false, 1, 2, 1, 4},
                {true,  1, 1, 1, 4},
                {false, 1, 1, 1, 1},
                {false, 4, 4, 4, 4}
        });
    }

    private boolean a;
    int[] arr;

    public verificationTest(boolean a, int b, int c, int d, int e) {
        this.a = a;
        this.arr = new int[] {b, c , d, e};
    }

    private MainClass mainClass;

    @Before
    public void init() {
        mainClass = new MainClass();
    }

    @Test
    public void verificationTest() {
        Assert.assertEquals(a, MainClass.verification(arr));
    }
}
