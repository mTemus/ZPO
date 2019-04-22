package classes.menu;

public class Pizza {

    private String name;
    private Ingredients firstIngredient = null;
    private Ingredients SecondIngredient = null;
    private Ingredients thirdIngredient = null;
    private Ingredients fourthIngredient = null;
    private Crust crust = null;
    private Size size = null;

    public enum Ingredients {
        TOMATO_SUACE,
        CHEESE,
        ONION,
        PINEAPPLE,
        BROCCOLI,
        BEAN,
        CORN,
        PEPERONI,
        MUSHROOMS,
        TOMATO,
        FETA,
        MOZARELLA,
        SUASAGE,
        CHICKEN,
        OLIVES,
        SALAMI,
        TUNA,
        BECON,
        ARUGULA,
        BLUE_CHEESE,
        HAM,
        BASIL,
        PEPPER,
        TABASCO_SUACE
    }

    public enum Crust {
        THIN, THICK, NORMAL, GLUTEN_FREE
    }

    public enum Size {
        SMALL, NORMAL, BIG, FAMILY
    }




    // <------- getters and setters ------->

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredients getFirstIngredient() {
        return firstIngredient;
    }

    public void setFirstIngredient(Ingredients firstIngredient) {
        this.firstIngredient = firstIngredient;
    }

    public Ingredients getSecondIngredient() {
        return SecondIngredient;
    }

    public void setSecondIngredient(Ingredients secondIngredient) {
        SecondIngredient = secondIngredient;
    }

    public Ingredients getThirdIngredient() {
        return thirdIngredient;
    }

    public void setThirdIngredient(Ingredients thirdIngredient) {
        this.thirdIngredient = thirdIngredient;
    }

    public Ingredients getFourthIngredient() {
        return fourthIngredient;
    }

    public void setFourthIngredient(Ingredients fourthIngredient) {
        this.fourthIngredient = fourthIngredient;
    }

    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
