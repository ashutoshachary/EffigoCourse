import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionExample {
    public static AtomicInteger count = new AtomicInteger(0);

    public static void increment() {
        count.incrementAndGet(); // Non-atomic increment can cause race condition
    }

    // 250

    // read nteh value

    public static int getCount() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        };

        Thread t1 = new Thread(incrementTask);
        Thread t2 = new Thread(incrementTask);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count (might be incorrect): " + getCount());
    }
}
