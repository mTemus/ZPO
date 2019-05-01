package Operations;

import Items.Item;

import java.util.List;

public class SingleThreadsOperations {
    private static TimeOperations TO = new TimeOperations();
    private static double now = 0;
    private static Thread tp;
    private static Thread tc;

    private static void singleThreadProduce(int singleThreadID, List<Item> items) {
        for (int i = singleThreadID; i < items.size(); i += 4)
            items.get(i).produceMe();
    }

    private static void singleThreadConsume(int singleThreadID, List<Item> items) {
        for (int i = singleThreadID; i < items.size(); i += 3)
            items.get(i).consumeMe();
    }

    public static void startSingleThreads(List<Item> items) {
        double previous = System.nanoTime();

        for (int i = 0; i < 4; i++) {
            int finalI1 = i;
            tp = new Thread(() -> singleThreadProduce(finalI1, items));
            tp.start();

            if (i < 3) {
                int finalI = i;
                tc = new Thread(() -> singleThreadConsume(finalI, items));
                tc.start();
            }
        }

        while (tp.isAlive() || tc.isAlive())
            now = System.nanoTime();

        TO.showActionTime(now, previous);
    }
}
