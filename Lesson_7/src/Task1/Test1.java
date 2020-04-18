package Task1;

public class Test1 {

    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @Test(priority = 3)
    public static void test1() {
        System.out.println("method1");
    }

    @Test
    public static void test2() {
        System.out.println("method2");
    }

    @Test
    public static void test3() {
        System.out.println("method3");
    }

    @Test(priority = 6)
    public static void test4() {
        System.out.println("method4");
    }

    @Test(priority = 4)
    public static void test5() {
        System.out.println("method5");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println("AfterSuite");
    }
}
