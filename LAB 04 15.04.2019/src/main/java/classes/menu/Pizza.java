package classes.menu;

public class Pizza {

    private String name;
    private Ingredients firstIngredient = null;
    private Ingredients SecondIngredient = null;
    private Ingredients thirdIngredient = null;
    private Ingredients fourthIngredient = null;
    private Ingredients fifthIngredient = null;

    private Crust crust = null;
    private Size size = null;

    public enum Ingredients {
        TOMATO_SAUCE,
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
        TABASCO_SAUCE
    }

    public enum Crust {
        THIN_CRUST, THICK_CRUST, NORMAL_CRUST, GLUTEN_FREE_CRUST
    }

    public enum Size {
        SMALL, NORMAL, BIG, FAMILY
    }

    public void makeSmall() {
        setSize(Size.SMALL);
        name += "small ";
    }

    public void makeNormal() {
        setSize(Size.NORMAL);
        name += "normal ";
    }

    public void makeBig() {
        setSize(Size.BIG);
        name += "big ";
    }

    public void makeFamily() {
        setSize(Size.FAMILY);
        name += "family ";
    }

    public void useThinCrust() {
        setCrust(Crust.THIN_CRUST);
        name += "thin crust ";
    }

    public void useThickCrust() {
        setCrust(Crust.THICK_CRUST);
        name += "thick crust ";
    }

    public void useNormalCrust() {
        setCrust(Crust.NORMAL_CRUST);
        name += "normal crust ";
    }

    public void useGlutenFreeCrust() {
        setCrust(Crust.GLUTEN_FREE_CRUST);
        name += "gluten free crust ";
    }

    public void makeCapri() {
        setFirstIngredient(Ingredients.HAM);
        setSecondIngredient(Ingredients.TOMATO_SAUCE);
        setThirdIngredient(Ingredients.MUSHROOMS);
        setFourthIngredient(Ingredients.CHEESE);
        setFifthIngredient(Ingredients.MUSHROOMS);
        name += "capri pizza";
    }

    public void makeVegan() {
        setFirstIngredient(Ingredients.ONION);
        setSecondIngredient(Ingredients.TOMATO_SAUCE);
        setThirdIngredient(Ingredients.BEAN);
        setFourthIngredient(Ingredients.CORN);
        setFifthIngredient(Ingredients.CHEESE);
        name += "vegan pizza";
    }

    public void makeFourCheese() {
        setFirstIngredient(Ingredients.CHEESE);
        setSecondIngredient(Ingredients.FETA);
        setThirdIngredient(Ingredients.TOMATO_SAUCE);
        setFourthIngredient(Ingredients.MOZARELLA);
        setFifthIngredient(Ingredients.BLUE_CHEESE);
        name += "margarita pizza";
    }

    public void makeHavai() {
        setFirstIngredient(Ingredients.CHEESE);
        setSecondIngredient(Ingredients.HAM);
        setThirdIngredient(Ingredients.TOMATO_SAUCE);
        setFourthIngredient(Ingredients.PINEAPPLE);
        setFifthIngredient(Ingredients.MUSHROOMS);
        name += "havai pizza";
    }

    public void addSauce() {
        if (getFirstIngredient() != null) {
            setFifthIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce ";
        } else if (getSecondIngredient() != null) {
            setSecondIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce ";
        } else if (getThirdIngredient() != null) {
            setThirdIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce ";
        } else if (getFourthIngredient() != null) {
            setFourthIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce ";
        } else if (getFifthIngredient() != null) {
            setFifthIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce pizza";
        }
    }

    public void addHam() {
        if (getFirstIngredient() != null) {
            setFifthIngredient(Ingredients.HAM);
            name += "ham ";
        } else if (getSecondIngredient() != null) {
            setSecondIngredient(Ingredients.HAM);
            name += "ham ";
        } else if (getThirdIngredient() != null) {
            setThirdIngredient(Ingredients.HAM);
            name += "ham ";
        } else if (getFourthIngredient() != null) {
            setFourthIngredient(Ingredients.HAM);
            name += "ham ";
        } else if (getFifthIngredient() != null) {
            setFifthIngredient(Ingredients.HAM);
            name += "ham pizza";
        }
    }

    public void addCheese() {
        if (getFirstIngredient() != null) {
            setFifthIngredient(Ingredients.CHEESE);
            name += "cheese ";
        } else if (getSecondIngredient() != null) {
            setSecondIngredient(Ingredients.CHEESE);
            name += "cheese ";
        } else if (getThirdIngredient() != null) {
            setThirdIngredient(Ingredients.CHEESE);
            name += "cheese ";
        } else if (getFourthIngredient() != null) {
            setFourthIngredient(Ingredients.CHEESE);
            name += "cheese ";
        } else if (getFifthIngredient() != null) {
            setFifthIngredient(Ingredients.CHEESE);
            name += "cheese pizza";
        }
    }

    public void addBroccoli() {
        if (getFirstIngredient() != null) {
            setFifthIngredient(Ingredients.BROCCOLI);
            name += "broccoli ";
        } else if (getSecondIngredient() != null) {
            setSecondIngredient(Ingredients.BROCCOLI);
            name += "broccoli ";
        } else if (getThirdIngredient() != null) {
            setThirdIngredient(Ingredients.BROCCOLI);
            name += "broccoli ";
        } else if (getFourthIngredient() != null) {
            setFourthIngredient(Ingredients.BROCCOLI);
            name += "broccoli ";
        } else if (getFifthIngredient() != null) {
            setFifthIngredient(Ingredients.BROCCOLI);
            name += "broccoli pizza";
        }
    }

    public void addPineapple() {
        if (getFirstIngredient() != null) {
            setFifthIngredient(Ingredients.PINEAPPLE);
            name += "pineapple ";
        } else if (getSecondIngredient() != null) {
            setSecondIngredient(Ingredients.PINEAPPLE);
            name += "pineapple ";
        } else if (getThirdIngredient() != null) {
            setThirdIngredient(Ingredients.PINEAPPLE);
            name += "pineapple ";
        } else if (getFourthIngredient() != null) {
            setFourthIngredient(Ingredients.PINEAPPLE);
            name += "pineapple ";
        } else if (getFifthIngredient() != null) {
            setFifthIngredient(Ingredients.PINEAPPLE);
            name += "pineapple pizza";
        }
    }

    public void addChicken() {
        if (getFirstIngredient() != null) {
            setFifthIngredient(Ingredients.CHICKEN);
            name += "chicken ";
        } else if (getSecondIngredient() != null) {
            setSecondIngredient(Ingredients.CHICKEN);
            name += "chicken ";
        } else if (getThirdIngredient() != null) {
            setThirdIngredient(Ingredients.CHICKEN);
            name += "chicken ";
        } else if (getFourthIngredient() != null) {
            setFourthIngredient(Ingredients.CHICKEN);
            name += "chicken ";
        } else if (getFifthIngredient() != null) {
            setFifthIngredient(Ingredients.CHICKEN);
            name += "chicken pizza";
        }
    }

    public boolean isPizzaReady() {
        return getFifthIngredient() != null &&
                getSecondIngredient() != null &&
                getThirdIngredient() != null &&
                getFourthIngredient() != null &&
                getFifthIngredient() != null;
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

    public Ingredients getFifthIngredient() {
        return fifthIngredient;
    }

    public void setFifthIngredient(Ingredients fifthIngredient) {
        this.fifthIngredient = fifthIngredient;
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
