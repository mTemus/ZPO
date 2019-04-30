package Operations;

import Items.Item;

import java.util.List;

public class MultiThreadsOperations {

    public static void runParallelStream(List<Item> items) {
        runParallelProducer(items);
        runParallelConsumer(items);
    }

    private static void runParallelProducer(List<Item> items) {
        items.parallelStream().forEach(Item::produceMe);
    }

    private static void runParallelConsumer(List<Item> items) {
        items.parallelStream().forEach(Item::consumeMe);
    }


}
