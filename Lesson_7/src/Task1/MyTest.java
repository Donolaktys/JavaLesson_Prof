package Task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyTest {
    private static Class testClass;

    public MyTest() {
    }

    public static void start(Class className) {
        try {
            List<Method> testList = new ArrayList<>();
            testClass = className;
            Method[] methods = testClass.getDeclaredMethods();

            // проверяем что есть не больше 1 метода отмеченного аннотациями @BeforeSuite и @AfterSuite
            int isBeforeSuite = 0;
            int isAfterSuite = 0;
            Method bs = null, as = null;
            for (Method m : methods) {
                if (m.isAnnotationPresent(BeforeSuite.class)) {
                    isBeforeSuite++;
                    bs = m;
                }
                if (m.isAnnotationPresent(AfterSuite.class)) {
                    isAfterSuite++;
                    as = m;
                }
                if (isBeforeSuite > 1 || isAfterSuite > 1)
                    throw new RuntimeException("Больше одной аннотации @BeforeSuite или @AfterSuite");
            }

            // выполняем метод @BeforeSuite если он есть (isBeforeSuite == 1)
            if (isBeforeSuite == 1) bs.invoke(null);

            // записываем методы @Test в ArrayList и сортируем по приоритету
            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    testList.add(m);
                }
            }
            testList.sort(Comparator.comparingInt((Method i) -> {
                return i.getAnnotation(Test.class).priority();
            }).reversed());

            // выполняем методы @Test
            for (Method mt : testList) {
                mt.invoke(null);
            }

            // выполняем метод @AfterSuite если он есть (isAfterSuite == 1)
            if (isAfterSuite == 1) as.invoke(null);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
