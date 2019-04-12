import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        List<Item> items = produce100_Items();



        System.out.println(items);
        System.out.println(items.size());

    }

    static List<Item> produce100_Items() {
        List<Item> list = new ArrayList<>();

        for (int i = 0; i < 100; i++)
            list.add(new Item());

        return list;
    }


}
