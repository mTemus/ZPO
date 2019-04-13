import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    static List<Item> items = produce100_Items();

    public static void main(String[] args) {

        int choice;
        boolean loop = true;

        Scanner scan = new Scanner(System.in);
        choice = scan.nextInt();


        while(loop){

            System.out.println("1. Single ");

            switch (choice){
                case 1:
                    Thread t1 = new Thread(() -> singleThreadProduce(0, 0));
                    Thread t2 = new Thread(() -> singleThreadProduce(1, 1));
                    Thread t3 = new Thread(() -> singleThreadProduce(2, 2));
                    Thread t4 = new Thread(() -> singleThreadProduce(3, 3));
                    t1.start();
                    t2.start();
                    t3.start();
                    t4.start();
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
        }

        System.out.println("Thread ID: " + singleThreadID + " have produced: " + produced + " items.");

    }

    public void singleThreadConsume() {


    }


}
