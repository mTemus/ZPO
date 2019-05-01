package Operations;

import Items.Item;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolOperations {
    private static double now = 0;
    private static double previous;
    private static int poolSize = 6;
    int queue = 0;
    int producerId = 0;
    int consumerId = 0;
    private TimeOperations TO = new TimeOperations();

    public void runThreadPool(List<Item> items) {
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

//        while (threadsManager.isTerminated()) {
//            if (threadsManager.isTerminated()) {
//                threadsManager.shutdown();
//                now = System.nanoTime();
//            }
//        }

        TO.showActionTime(previous, now);
    }

    private void manageThreads(List<Item> items, int size) throws InterruptedException {
        size /= 2;
        if (queue > 1) {
            queue = 0;
        }
        if (queue == 0) {
            ++queue;
            multiThreadProduce(producerId++, items, size);
        } else if (queue == 1) {
            ++queue;
            Thread.sleep(1);
            multiThreadConsume(consumerId++, items, size);
        }


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
