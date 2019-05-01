package Operations;

import Items.Item;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolOperations {
    private static double previous;
    private static int poolSize = 12;
    private int working = 0;
    private int producerId = 0;
    private static int consumerId = 0;
    private static TimeOperations TO = new TimeOperations();

    public void runFixedThreadPool(List<Item> items) {
        setThreadPool();
        previous = System.nanoTime();
        ExecutorService threadsManager = Executors.newFixedThreadPool(poolSize);

        for (int i = 0; i < poolSize; i++) {
            threadsManager.execute(new Thread(() -> {
                try {
                    manageThreads(items, poolSize);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ));
        }
        threadsManager.shutdown();
    }

    private void setThreadPool() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please write amount of threads in thread pool, but please take a even number:");
        int pool = scan.nextInt();

        while (pool % 2 != 0) {
            System.out.println("Please take a even number: ");
            pool = scan.nextInt();
        }
        poolSize = pool;

    }

    private void manageThreads(List<Item> items, int size) throws InterruptedException {
        size /= 2;
        if (working > 1) {
            working = 0;
        }
        if (working == 0) {
            ++working;
            multiThreadProduce(producerId++, items, size);
        } else if (working == 1) {
            ++working;
            Thread.sleep(1);
            multiThreadConsume(consumerId++, items, size);
        }
    }

    private static void multiThreadProduce(int singleThreadID, List<Item> items, int size) {
        for (int i = singleThreadID; i < items.size(); i += size)
            items.get(i).produceMe();
    }

    private static void multiThreadConsume(int singleThreadID, List<Item> items, int size) {
        for (int i = singleThreadID; i < items.size(); i += size)
            items.get(i).consumeMe();
        double now = System.nanoTime();

        if (singleThreadID == (consumerId - 1))
            TO.showActionTime(now, previous);
    }
}
