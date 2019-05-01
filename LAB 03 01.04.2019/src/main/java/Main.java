import Items.Item;
import Operations.MultiThreadsOperations;
import Operations.SingleThreadsOperations;
import Operations.ThreadPoolOperations;
import Operations.TimeOperations;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    private static double now = 0;
    private static double previous;

    private static TimeOperations TO = new TimeOperations();
    private static ThreadPoolOperations TPO = new ThreadPoolOperations();


    private static volatile List<Item> items = produce100_Items();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int choice;
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Seven threads (4 producers, 3 consumers). ");
        System.out.println("2. Parallel streams. ");
        System.out.println("3. Fixed threads pool. ");
        System.out.println("Chose your option.");
        choice = scan.nextInt();

        switch (choice) {
            case 1:
                SingleThreadsOperations.startSingleThreads(items);
                break;
            case 2:
                previous = System.nanoTime();
                MultiThreadsOperations.runParallelStream(items);
                now = System.nanoTime();
                TO.showActionTime(now, previous);
                break;
            case 3:
                TPO.runFixedThreadPool(items);
                break;
            default:
                System.out.println("Switch error");
                break;
        }
    }

    private static void parallelMultithreading() {
        double previous = System.nanoTime();
        ForkJoinPool customThreadPool = new ForkJoinPool(2);
        customThreadPool.submit(
                () -> items.parallelStream().forEach(Item::produceMe));


        if (customThreadPool.isShutdown()) {
            now = System.nanoTime();
            double timerTime = (now - previous) / 1000000000;
            System.out.println("Time: " + timerTime + " s.");
        }
    }

    private static synchronized void runThreads(int id) throws InterruptedException {
        System.out.println("Thread ID: " + Thread.currentThread().getName() + " started.");

        items.parallelStream().forEach(Item::produceMe);

        System.out.println("Thread ID: " + Thread.currentThread().getName() + " stopped.");
    }


    /// sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss


    private static List<Item> produce100_Items() {
        return Stream
                .generate(Item::new)
                .limit(100)
                .collect(Collectors.toList());
    }


}
