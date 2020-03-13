public class Main {
    static Object monitor = new Object();
    static volatile char currentChar = 'A';
    static final int maxIteration = 5;

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                for (int i = 0; i < maxIteration; i++) {
                    synchronized (monitor) {
                        while (currentChar != 'A') {
                            monitor.wait();
                        }
                        System.out.print(currentChar);
                        inc();
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < maxIteration; i++) {
                    synchronized (monitor) {
                        while (currentChar != 'B') {
                            monitor.wait();
                        }
                        System.out.print(currentChar);
                        inc();
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < maxIteration; i++) {
                    synchronized (monitor) {
                        while (currentChar != 'C') {
                            monitor.wait();
                        }
                        System.out.print(currentChar + " ");
                        inc();
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void inc() {
        currentChar++;
        if (currentChar > 'C') {
            currentChar = 'A';
        }
    }
}

/**
 * ABC ABC ABC ABC ABC
 * Process finished with exit code 0
 */
