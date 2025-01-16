public class Main {

    // public static class ChildThreadTask implements Runnable {
    // @Override
    // public void run() {
    // System.out.println("Child thread is running");
    // count();
    // }
    // }

    public static void main(String[] args) {
        Thread chiThread = new Thread(() -> count());
        chiThread.start();
        count();
    }

    public static void count() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}