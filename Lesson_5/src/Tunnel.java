import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore smp;

    public Tunnel(int length, Semaphore smp) {
        this.length = length;
        this.description = "Тоннель " + length + " метров";
        this.smp = smp;
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            smp.acquire();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            smp.release();
        }
    }
}
