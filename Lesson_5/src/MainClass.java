import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final int TUNNEL_CAPACITY = CARS_COUNT / 2;
    public static CyclicBarrier car_cb;
    public static Semaphore tunnel_smp;
    public static CountDownLatch cdl;

    public static void main(String[] args) {
        car_cb = new CyclicBarrier(CARS_COUNT);
        tunnel_smp = new Semaphore(TUNNEL_CAPACITY);
        cdl = new CountDownLatch(CARS_COUNT);

        try {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            Race race = new Race(new Road(60), new Tunnel(80, tunnel_smp), new Road(40));
            Car[] cars = new Car[CARS_COUNT];
            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(race, 20 + (int) (Math.random() * 10), car_cb);
            }

            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
            }

            cdl.await();
            cdl = new CountDownLatch(CARS_COUNT);
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            cdl.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!
 Участник #1 готовится
 Участник #2 готовится
 Участник #3 готовится
 Участник #4 готовится
 Участник #1 готов
 Участник #3 готов
 Участник #4 готов
 Участник #2 готов
 ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!
 Участник #1 начал этап: Дорога 60 метров
 Участник #4 начал этап: Дорога 60 метров
 Участник #3 начал этап: Дорога 60 метров
 Участник #2 начал этап: Дорога 60 метров
 Участник #3 закончил этап: Дорога 60 метров
 Участник #4 закончил этап: Дорога 60 метров
 Участник #4 готовится к этапу(ждет): Тоннель 80 метров
 Участник #2 закончил этап: Дорога 60 метров
 Участник #1 закончил этап: Дорога 60 метров
 Участник #2 готовится к этапу(ждет): Тоннель 80 метров
 Участник #4 начал этап: Тоннель 80 метров
 Участник #3 готовится к этапу(ждет): Тоннель 80 метров
 Участник #2 начал этап: Тоннель 80 метров
 Участник #1 готовится к этапу(ждет): Тоннель 80 метров
 Участник #4 закончил этап: Тоннель 80 метров
 Участник #3 начал этап: Тоннель 80 метров
 Участник #4 начал этап: Дорога 40 метров
 Участник #2 закончил этап: Тоннель 80 метров
 Участник #1 начал этап: Тоннель 80 метров
 Участник #2 начал этап: Дорога 40 метров
 Участник #4 закончил этап: Дорога 40 метров
 Участник #2 закончил этап: Дорога 40 метров
 Участник #3 закончил этап: Тоннель 80 метров
 Участник #3 начал этап: Дорога 40 метров
 Участник #1 закончил этап: Тоннель 80 метров
 Участник #1 начал этап: Дорога 40 метров
 Участник #3 закончил этап: Дорога 40 метров
 Участник #1 закончил этап: Дорога 40 метров
 ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!

 Process finished with exit code 0
 */