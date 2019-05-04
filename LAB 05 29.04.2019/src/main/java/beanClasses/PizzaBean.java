package beanClasses;

import java.io.Serializable;
import java.time.LocalDate;

public class PizzaBean implements Serializable {
    private int price;
    private String name;
    private LocalDate dateOfMade;
    private PizzaType pizzaType;

    private enum PizzaType {
        HAVAI, VEGETARIAN, CAPRI, MARGARITA, EXTRA_HOT;
    }

    public PizzaBean() {
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

    public LocalDate getDateOfMade() {
        return dateOfMade;
    }

    public void setDateOfMade(LocalDate dateOfMade) {
        this.dateOfMade = dateOfMade;
    }

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }
}
