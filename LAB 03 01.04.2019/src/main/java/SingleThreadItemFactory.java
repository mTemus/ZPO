public class SingleThreadItemFactory implements Runnable {

    public int id;
    boolean producing;
    boolean consuming;

    Main m = new Main();

    public SingleThreadItemFactory(int id, boolean producing) {
        this.id = id;
        this.producing = producing;
    }

    @Override
    public void run() {

        if (producing)
            produce();
        else if (!producing)
            consume();
        else
            System.out.println("Thread type error.");
    }

    public void produce() {
        for (Item i : m.items) {
            if (!i.isProduced()) {
                i.produceMe();
                System.out.println(id);
            }
        }
    }

    public void consume() {
        for (Item i : m.items) {
            i.consumeMe();
            System.out.println(id);
        }

    }


}
