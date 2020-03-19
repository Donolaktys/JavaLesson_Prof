import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static AtomicInteger ai;

    static {
        CARS_COUNT = 0;
        ai = new AtomicInteger(0);
    }

    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed, CyclicBarrier cb) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");

            MainClass.cdl.countDown();
            MainClass.car_cb.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            if (ai.incrementAndGet() == 1) {
                System.out.println(this.name + " WIN!!!!!");
            }

            MainClass.cdl.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
