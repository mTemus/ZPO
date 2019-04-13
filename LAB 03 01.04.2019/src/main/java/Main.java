import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static double now = 0;
    static double previous = 0;
    static double timeTimer = 0;

    static List<Item> items = produce100_Items();
    static Timer singleThreadsTimer = new Timer();

    public static void main(String[] args) {
        int choice;
        boolean loop = true;
        Scanner scan = new Scanner(System.in);


        while (loop) {

            System.out.println("1. Single threads application. ");
            System.out.println("2. Thread pool application. ");
            System.out.println("3. Exit.");
            System.out.println("Chose your option.");
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    startSingleThreads();

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

//        int produced = 0;


        for (int i = startFrom; i < items.size(); i += 4) {
//            System.out.println("Thread ID " + singleThreadID + " is:");
            items.get(i).produceMe();
//            produced++;
        }

//        System.out.println("Thread ID: " + singleThreadID + " have produced: " + produced + " items.");

    }

    public static void singleThreadConsume(int singleThreadID, int startFrom) {

//        int consumed = 0;

        for (int i = startFrom; i < items.size(); i += 3) {
//            System.out.println("Thread ID " + singleThreadID + " is:");
            items.get(i).consumeMe();
//            consumed++;
        }

//        System.out.println("Thread ID: " + singleThreadID + " have consumed: " + consumed + " items.");


    }

    public static void startSingleThreads() {

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
        previous = System.nanoTime();

        if (!threadsAreAlive(producer1, producer2, producer3, producer4, consumer1, consumer2, consumer3))
            calculateSingleThreadsTime();
    }

    public static boolean threadsAreAlive(Thread p1, Thread p2, Thread p3, Thread p4, Thread c1, Thread c2, Thread c3) {

        if (p1.isAlive() || p2.isAlive() || p3.isAlive() || p4.isAlive() || c1.isAlive() || c2.isAlive() || c3.isAlive())
            return true;
        else
            return false;
    }

    public static void calculateSingleThreadsTime() {
        now = System.nanoTime();
        timeTimer /= 1000000000;

        System.out.println("Threads time (seconds) : " + timeTimer);
        timeTimer /= 60;

        System.out.println("Threads time (minutes) : " + timeTimer);
    }

}
