package classes.storage;

import java.util.Random;

public class Item {


    private String name;
    private int price;

    private boolean produced = false;
    private boolean consumed = false;
    private boolean sold = false;

    private String generatePrice() {
        Random randomPrice = new Random();

        price = randomPrice.nextInt() * 10 + randomPrice.nextInt() * 100 + randomPrice.nextInt() * 1000;

        return "New price of the item is: " + price + "$.";
    }

    private String checkItem() {
        String itemCondition = "";

        if (produced)
            itemCondition += "item is produced, ";
        else
            itemCondition += "item is not produced, ";

        if (consumed)
            itemCondition += "item is consumed, ";
        else
            itemCondition += "item is not consumed, ";

        if (sold)
            itemCondition += "item is sold, ";
        else
            itemCondition += "item is not sold.";

        return itemCondition;
    }


    private void produceItem() {
        produced = true;
    }

    private void consumeItem() {
        consumed = true;
    }

    private void sellItem() {
        sold = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isProduced() {
        return produced;
    }

    public void setProduced(boolean produced) {
        this.produced = produced;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
