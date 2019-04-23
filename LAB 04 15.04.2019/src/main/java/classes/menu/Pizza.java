package classes.menu;

public class Pizza {

    private String name = "";
    private static Ingredients firstIngredient;
    private static Ingredients secondIngredient;
    private static Ingredients thirdIngredient;
    private static Ingredients fourthIngredient;
    private static Ingredients fifthIngredient;

    private Crust crust = null;
    private Size size = null;
    private Creator creator = null;

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
        MOZZARELLA,
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

    public enum Creator {
        OWN, MENU
    }

    public String makeSmall() {
        setSize(Size.SMALL);
        name += "small ";
        return name;
    }

    public String makeNormal() {
        setSize(Size.NORMAL);
        name += "normal size ";
        return name;
    }

    public String makeBig() {
        setSize(Size.BIG);
        name += "big ";
        return name;
    }

    public String makeFamily() {
        setSize(Size.FAMILY);
        name += "family ";
        return name;
    }

    public String useThinCrust() {
        setCrust(Crust.THIN_CRUST);
        name += "thin crust ";
        return name;
    }

    public String useThickCrust() {
        setCrust(Crust.THICK_CRUST);
        name += "thick crust ";
        return name;
    }

    public String useNormalCrust() {
        setCrust(Crust.NORMAL_CRUST);
        name += "normal crust ";
        return name;
    }

    public String useGlutenFreeCrust() {
        setCrust(Crust.GLUTEN_FREE_CRUST);
        name += "gluten free crust ";
        return name;
    }

    public String makeCapri() {
        setCreator(Creator.MENU);
        setFirstIngredient(Ingredients.HAM);
        setSecondIngredient(Ingredients.TOMATO_SAUCE);
        setThirdIngredient(Ingredients.MUSHROOMS);
        setFourthIngredient(Ingredients.CHEESE);
        setFifthIngredient(Ingredients.MUSHROOMS);
        name += "capri pizza";
        return name;
    }

    public String makeVegan() {
        setCreator(Creator.MENU);
        setFirstIngredient(Ingredients.ONION);
        setSecondIngredient(Ingredients.TOMATO_SAUCE);
        setThirdIngredient(Ingredients.BEAN);
        setFourthIngredient(Ingredients.CORN);
        setFifthIngredient(Ingredients.CHEESE);
        name += "vegan pizza";
        return name;
    }

    public String makeFourCheese() {
        setCreator(Creator.MENU);
        setFirstIngredient(Ingredients.CHEESE);
        setSecondIngredient(Ingredients.FETA);
        setThirdIngredient(Ingredients.TOMATO_SAUCE);
        setFourthIngredient(Ingredients.MOZZARELLA);
        setFifthIngredient(Ingredients.BLUE_CHEESE);
        name += "margarita pizza";
        return name;
    }

    public String makeHavai() {
        setCreator(Creator.MENU);
        setFirstIngredient(Ingredients.CHEESE);
        setSecondIngredient(Ingredients.HAM);
        setThirdIngredient(Ingredients.TOMATO_SAUCE);
        setFourthIngredient(Ingredients.PINEAPPLE);
        setFifthIngredient(Ingredients.MUSHROOMS);
        name += "havai pizza";
        return name;
    }

    public String addSauce() {
        setCreator(Creator.OWN);

        if (getFirstIngredient() == null) {
            setFirstIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce ";
        } else if (getSecondIngredient() == null) {
            setSecondIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce ";
        } else if (getThirdIngredient() == null) {
            setThirdIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce ";
        } else if (getFourthIngredient() == null) {
            setFourthIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce ";
        } else if (getFifthIngredient() == null) {
            setFifthIngredient(Ingredients.TOMATO_SAUCE);
            name += "tomato sauce pizza";
        }
        return name;
    }

    public String addHam() {
        setCreator(Creator.OWN);

        if (getFirstIngredient() == null) {
            setFirstIngredient(Ingredients.HAM);
            name += "ham ";
        } else if (getSecondIngredient() == null) {
            setSecondIngredient(Ingredients.HAM);
            name += "ham ";
        } else if (getThirdIngredient() == null) {
            setThirdIngredient(Ingredients.HAM);
            name += "ham ";
        } else if (getFourthIngredient() == null) {
            setFourthIngredient(Ingredients.HAM);
            name += "ham ";
        } else if (getFifthIngredient() == null) {
            setFifthIngredient(Ingredients.HAM);
            name += "ham pizza";
        }

        return name;
    }

    public String addCheese() {
        setCreator(Creator.OWN);

        if (getFirstIngredient() == null) {
            setFirstIngredient(Ingredients.CHEESE);
            name += "cheese ";
        } else if (getSecondIngredient() == null) {
            setSecondIngredient(Ingredients.CHEESE);
            name += "cheese ";
        } else if (getThirdIngredient() == null) {
            setThirdIngredient(Ingredients.CHEESE);
            name += "cheese ";
        } else if (getFourthIngredient() == null) {
            setFourthIngredient(Ingredients.CHEESE);
            name += "cheese ";
        } else if (getFifthIngredient() == null) {
            setFifthIngredient(Ingredients.CHEESE);
            name += "cheese pizza";
        }
        return name;
    }

    public String addBroccoli() {
        setCreator(Creator.OWN);

        if (getFirstIngredient() == null) {
            setFirstIngredient(Ingredients.BROCCOLI);
            name += "broccoli ";
        } else if (getSecondIngredient() == null) {
            setSecondIngredient(Ingredients.BROCCOLI);
            name += "broccoli ";
        } else if (getThirdIngredient() == null) {
            setThirdIngredient(Ingredients.BROCCOLI);
            name += "broccoli ";
        } else if (getFourthIngredient() == null) {
            setFourthIngredient(Ingredients.BROCCOLI);
            name += "broccoli ";
        } else if (getFifthIngredient() == null) {
            setFifthIngredient(Ingredients.BROCCOLI);
            name += "broccoli pizza";
        }
        return name;
    }

    public String addPineapple() {
        setCreator(Creator.OWN);

        if (getFirstIngredient() == null) {
            setFirstIngredient(Ingredients.PINEAPPLE);
            name += "pineapple ";
        } else if (getSecondIngredient() == null) {
            setSecondIngredient(Ingredients.PINEAPPLE);
            name += "pineapple ";
        } else if (getThirdIngredient() == null) {
            setThirdIngredient(Ingredients.PINEAPPLE);
            name += "pineapple ";
        } else if (getFourthIngredient() == null) {
            setFourthIngredient(Ingredients.PINEAPPLE);
            name += "pineapple ";
        } else if (getFifthIngredient() == null) {
            setFifthIngredient(Ingredients.PINEAPPLE);
            name += "pineapple pizza";
        }
        return name;
    }

    public String addChicken() {
        setCreator(Creator.OWN);

        if (getFirstIngredient() == null) {
            setFirstIngredient(Ingredients.CHICKEN);
            name += "chicken ";
        } else if (getSecondIngredient() == null) {
            setSecondIngredient(Ingredients.CHICKEN);
            name += "chicken ";
        } else if (getThirdIngredient() == null) {
            setThirdIngredient(Ingredients.CHICKEN);
            name += "chicken ";
        } else if (getFourthIngredient() == null) {
            setFourthIngredient(Ingredients.CHICKEN);
            name += "chicken ";
        } else if (getFifthIngredient() == null) {
            setFifthIngredient(Ingredients.CHICKEN);
            name += "chicken pizza";
        }
        return name;
    }

    private boolean isPizzaReady() {
        return getCrust() != null &&
                getSize() != null;
    }

    private boolean areIngredientsReady() {
        return getFirstIngredient() != null &&
                getSecondIngredient() != null &&
                getThirdIngredient() != null;
    }

    public String haveGoodMeal() {
        if (isPizzaReady() && creator == Creator.MENU)
            return name;
        else if (isPizzaReady() && areIngredientsReady() && creator == Creator.OWN)
            return name;
        else return "Pizza is still in progress.";
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
        return secondIngredient;
    }

    public void setSecondIngredient(Ingredients secondIngredient) {
        this.secondIngredient = secondIngredient;
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

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}
