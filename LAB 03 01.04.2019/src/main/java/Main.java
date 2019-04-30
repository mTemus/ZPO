import Items.Item;
import Operations.SingleThreadsOperations;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    static SingleThreadsOperations STO = new SingleThreadsOperations();

    private static double now = 0;

    private static volatile List<Item> items = produce100_Items();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int choice;
        Scanner scan = new Scanner(System.in);

        System.out.println("1. Single threads application. ");
        System.out.println("2. Thread pool application. ");
        System.out.println("Chose your option.");
        choice = scan.nextInt();

        switch (choice) {
            case 1:
                STO.startSingleThreads(items);
                break;
            case 2:
                parallelMultithreading();
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


//        ExecutorService threadsManager = Executors.newFixedThreadPool(2);
//
//
//        for (int i = 0; i < 2; i++) {
//            int finalI = i;
//            threadsManager.execute(new Thread(() -> {
//                try {
//                    runThreads(finalI);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }));
//        }
//
//        threadsManager.shutdown();


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
