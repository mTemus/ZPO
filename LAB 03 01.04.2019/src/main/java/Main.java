import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    static List<Item> items = produce100_Items();
    static int timerSeconds = 0;

    public static void main(String[] args) {


        int choice;
        boolean loop = true;
        Scanner scan = new Scanner(System.in);

        while (loop) {

            System.out.println("1. Single threads application. ");
            System.out.println("2. Thread pool application. ");
            System.out.println("3. Exit.");
            System.out.println("Take your option.");
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    Thread producer1 = new Thread(() -> singleThreadProduce(0, 0));
                    Thread producer2 = new Thread(() -> singleThreadProduce(1, 1));
                    Thread producer3 = new Thread(() -> singleThreadProduce(2, 2));
                    Thread producer4 = new Thread(() -> singleThreadProduce(3, 3));

                    Thread consumer1 = new Thread(() -> singleThreadConsume(0, 0));
                    Thread consumer2 = new Thread(() -> singleThreadConsume(1, 1));
                    Thread consumer3 = new Thread(() -> singleThreadConsume(2, 2));
                    producer1.start();
                    producer2.start();
                    producer3.start();
                    producer4.start();

                    consumer1.start();
                    consumer2.start();
                    consumer3.start();

                    break;
                case 2:
                    break;
                case 3:
                    loop = false;
                    break;
                default:
                    break;
            }
        }


//        ExecutorService executor = Executors.newFixedThreadPool(0);


    }


    static List<Item> produce100_Items() {
        List<Item> list = Stream
                .generate(() -> new Item())
                .limit(100)
                .collect(Collectors.toList());
        return list;
    }

    public static void singleThreadProduce(int singleThreadID, int startFrom) {

        int produced = 0;


        for (int i = startFrom; i < items.size(); i += 4) {
            System.out.println("Thread ID " + singleThreadID + " is:");
            items.get(i).produceMe();
            produced++;
            timerSeconds++;
        }

        System.out.println("Thread ID: " + singleThreadID + " have produced: " + produced + " items.");

    }

    public static void singleThreadConsume(int singleThreadID, int startFrom) {

        int consumed = 0;

        for (int i = startFrom; i < items.size(); i += 3) {
            System.out.println("Thread ID " + singleThreadID + " is:");
            items.get(i).consumeMe();
            consumed++;
        }

        System.out.println("Thread ID: " + singleThreadID + " have consumed: " + consumed + " items.");


    }


}
