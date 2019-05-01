package Operations;

import Items.Item;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolOperations {
    private static double now = 0;
    private static double previous;
    private static int poolSize = 5;
    private TimeOperations TO = new TimeOperations();

    public void runThreadPool(List<Item> items) {
        previous = System.nanoTime();
        ExecutorService threadsManager = Executors.newFixedThreadPool(poolSize);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            threadsManager.execute(new Thread(() -> {
                multiThreadProduce(finalI, items, poolSize);
                multiThreadConsume(finalI, items, poolSize);
            }
            ));
        }

        if (threadsManager.isTerminated())
            threadsManager.shutdown();
        while (!threadsManager.isShutdown())
            now = System.nanoTime();
        TO.showActionTime(previous, now);
    }

    private static void multiThreadProduce(int singleThreadID, List<Item> items, int size) {
        System.out.println(Thread.currentThread().getName());

        for (int i = singleThreadID; i < items.size(); i += size)
            items.get(i).produceMe();
    }

    private static void multiThreadConsume(int singleThreadID, List<Item> items, int size) {
        for (int i = singleThreadID; i < items.size(); i += size)
            items.get(i).consumeMe();
    }

}
