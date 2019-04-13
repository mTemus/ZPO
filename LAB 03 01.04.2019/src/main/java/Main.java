import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    private static double now = 0;

    private static List<Item> items = produce100_Items();

    public static void main(String[] args) {
        int choice;
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Single threads application. ");
        System.out.println("2. Thread pool application. ");
        System.out.println("Chose your option.");
        choice = scan.nextInt();

        switch (choice) {
            case 1:
                startSingleThreads();
                break;
            case 2:
//                ExecutorService threadsManager = Executors.newFixedThreadPool(2);
//
//                double previous = System.nanoTime();
//                for (int i = 0; i < 10; i++) {
//                    int finalI = i;
//                    threadsManager.execute(new Thread(() -> runThreads(finalI)));
//                }
//
//
//                threadsManager.shutdown();
//                if (threadsManager.isShutdown()) {
//                    now = System.nanoTime();
//                    double timerTime = (now - previous) / 1000000000;
//                    System.out.println("Time: " + timerTime + " s.");
//                }


                break;
            default:
                System.out.println("Switch error");
                break;
        }
    }

    private static void runThreads(int id) {
        System.out.println("Thread ID: " + Thread.currentThread().getName() + " started.");

        items.parallelStream()
                .filter(Item::isNotProduced)
                .forEach(Item::produceMe);


        System.out.println("Thread ID: " + Thread.currentThread().getName() + " stopped.");
    }

    private static List<Item> produce100_Items() {
        return Stream
                .generate(Item::new)
                .limit(100)
                .collect(Collectors.toList());
    }

    private static void singleThreadProduce(int singleThreadID) {
        for (int i = singleThreadID; i < items.size(); i += 4)
            items.get(i).produceMe();
    }

    private static void singleThreadConsume(int singleThreadID) {
        for (int i = singleThreadID; i < items.size(); i += 3)
            items.get(i).consumeMe();
    }

    private static void startSingleThreads() {
        Thread producer1 = new Thread(() -> singleThreadProduce(0));
        Thread producer2 = new Thread(() -> singleThreadProduce(1));
        Thread producer3 = new Thread(() -> singleThreadProduce(2));
        Thread producer4 = new Thread(() -> singleThreadProduce(3));

        Thread consumer1 = new Thread(() -> singleThreadConsume(0));
        Thread consumer2 = new Thread(() -> singleThreadConsume(1));
        Thread consumer3 = new Thread(() -> singleThreadConsume(2));

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
        double previous = System.nanoTime();

        while (threadsAreAlive(producer1, producer2, producer3, producer4, consumer1, consumer2, consumer3)) {
            now = System.nanoTime();
        }
        double timeTimer = (now - previous) / 1000000000;

        System.out.println("Threads took time: " + timeTimer + "s.");
    }

    private static boolean threadsAreAlive(Thread p1, Thread p2, Thread p3, Thread p4, Thread c1, Thread c2, Thread c3) {

        return p1.isAlive() || p2.isAlive() || p3.isAlive() || p4.isAlive() || c1.isAlive() || c2.isAlive() || c3.isAlive();
    }

}
