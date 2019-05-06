package beanClasses;

import java.io.Serializable;
import java.time.LocalDate;

public class ItemBean implements Serializable {

    private int price;
    private String name;
    private LocalDate productionDate;
    private ItemType type;

    private enum ItemType {
        GAME, BOOK, SMARTPHONE, PLUSH;
    }

    public ItemBean() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
