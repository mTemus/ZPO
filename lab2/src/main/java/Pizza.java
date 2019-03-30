import java.util.Arrays;
import java.util.List;

public enum Pizza{
    MARGHERITA("Marrgherita", Ingredients.THIN_CRUST, Ingredients.TOMATO_SUACE,
            Ingredients.CHEESE),
    CAPRI("Capri", Ingredients.THIN_CRUST, Ingredients.TOMATO_SUACE, Ingredients.CHEESE, Ingredients.HAM,
            Ingredients.MUSHROOMS),
    HAVAI("Havai", Ingredients.THIN_CRUST, Ingredients.TOMATO_SUACE, Ingredients.CHEESE,
            Ingredients.HAM,Ingredients.PINEAPPLE),
    CARUSO("Caruso", Ingredients.THIN_CRUST, Ingredients.TOMATO_SUACE, Ingredients.SUASAGE,
            Ingredients.PEPERONI),
    MAMA_MIA("Mama Mia", Ingredients.THIN_CRUST, Ingredients.TOMATO_SUACE, Ingredients.CHEESE,
            Ingredients.ONION, Ingredients.MUSHROOMS, Ingredients.BECON),
    SOPRANO("Soprano", Ingredients.THICK_CRUST, Ingredients.TOMATO_SUACE, Ingredients.CHEESE, Ingredients.HAM,
            Ingredients.MUSHROOMS, Ingredients.ONION, Ingredients.BECON, Ingredients.PEPPER),
    CALABRESE("Calabrese", Ingredients.THICK_CRUST, Ingredients.TOMATO_SUACE, Ingredients.CHEESE,
            Ingredients.HAM, Ingredients.MUSHROOMS, Ingredients.SUASAGE, Ingredients.ONION , Ingredients.OLIVES),
    VEGETARIANA("Vegetariana", Ingredients.THIN_CRUST, Ingredients.TOMATO_SUACE,
            Ingredients.CHEESE,
            Ingredients.ONION, Ingredients.BEAN, Ingredients.CORN, Ingredients.BROCCOLI, Ingredients.ARUGULA),
    CAPRESE("Caprese", Ingredients.THICK_CRUST, Ingredients.TOMATO_SUACE,
            Ingredients.MOZARELLA,Ingredients.FETA, Ingredients.TOMATO, Ingredients.BASIL),
    PASCETORE("Pascetore", Ingredients.THIN_CRUST, Ingredients.TOMATO_SUACE, Ingredients.CHEESE,
            Ingredients.TUNA, Ingredients.ONION),
    FOUR_CHEESE("Cztery sery", Ingredients.THIN_CRUST, Ingredients.TOMATO_SUACE,
            Ingredients.CHEESE,Ingredients.MOZARELLA, Ingredients.FETA, Ingredients.BLUE_CHEESE),
    TABASCO("Tabasco", Ingredients.THICK_CRUST, Ingredients.TOMATO_SUACE, Ingredients.CHEESE, Ingredients.HAM,
            Ingredients.SALAMI, Ingredients.PEPERONI, Ingredients.CORN, Ingredients.TABASCO_SUACE),
    AMORE("Amore", Ingredients.THIN_CRUST, Ingredients.TOMATO_SUACE, Ingredients.CHEESE,
            Ingredients.CHICKEN,Ingredients.TOMATO),
    FARMER("Farmerska", Ingredients.THICK_CRUST, Ingredients.TOMATO_SUACE, Ingredients.CHEESE,
            Ingredients.CHICKEN, Ingredients.BECON, Ingredients.ONION, Ingredients.CORN);
    private final String name;
    private final List<Ingredients> ingredients;
    private Pizza(String name, Ingredients... ingredients) {
        this.name = name;
        this.ingredients = Arrays.asList(ingredients);
    }
    public String getName() {
        return name;
    }
    public List<Ingredients> getIngredients() {
        return ingredients;
    }
    @Override
    public String toString() {
        return name;
    }
}
