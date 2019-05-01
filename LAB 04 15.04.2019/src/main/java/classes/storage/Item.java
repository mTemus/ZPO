package classes.storage;

import java.util.Random;

public class Item {
    private String name;
    private int price;

    private boolean produced = false;
    private boolean consumed = false;
    private boolean sold = false;

    public String generatePrice() {
        Random randomPrice = new Random();
        String strPrice;

        price = randomPrice.nextInt() * 10 + randomPrice.nextInt() * 100 + randomPrice.nextInt() * 1000;
        strPrice = "New price of the item is: " + price + "$.";

        return strPrice;
    }

    public String checkItem() {
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

    public String produceItem() {
        String itemCondition = "Item produced.";
        produced = true;
        return itemCondition;
    }

    public String consumeItem() {
        String itemCondition = "Item consumed.";
        consumed = true;
        return itemCondition;
    }

    public String sellItem() {
        String itemCondition = "Item sold.";
        sold = true;
        return itemCondition;
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
