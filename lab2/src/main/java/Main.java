import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        boolean x = true;

        while(x) {
            System.out.println("1.Najtańsza ostra");
            System.out.println("2.Najdroższa wegetariańska");
            System.out.println("3.Pizze mięsne");
            System.out.println("4.Grupowanie Pizzy po cenie");
            System.out.println("5.Menu");
            System.out.println("6.Wyjscie");
            System.out.println("Podaj operację");

            int input = scan.nextInt();

            switch (input){
                case 1:
                    findCheapestSpicy();
                    break;
                case 2:
                    findMostExpensiveVegetarian();
                    break;
                case 3:
                    iLikeMeat();
                    break;
                case 4:
                    groupByPrice();
                    break;
                case 5:
                    formatedMenu();
                    break;
                case 6:
                    x = false;
                    break;

            }
        }

    }

    public static Pizza findCheapestSpicy(){
        Pizza p = Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients().stream().anyMatch(i -> i.isSpicy()))
                .sorted((Pizza p1, Pizza p2) -> p1.getIngredients().stream().mapToInt(Ingredients::getPrice).sum() -
                        p2.getIngredients().stream().mapToInt(Ingredients::getPrice).sum())
                .findFirst()
                .orElse(null);
        System.out.println(p);
        return p;
    }

    public static Pizza findMostExpensiveVegetarian(){
        Pizza p = Arrays.stream(Pizza.values())
                .filter(pizza -> pizza.getIngredients().stream().noneMatch(i -> i.isMeat()))
                .max((Pizza p1, Pizza p2) -> p1.getIngredients().stream().mapToInt(Ingredients::getPrice).sum() -
                        p2.getIngredients().stream().mapToInt(Ingredients::getPrice).sum())
                .orElse(null);
        System.out.println(p);
        return p;

    }

    public static List<Pizza> iLikeMeat(){
        List<Pizza> p=Arrays.stream(Pizza.values())
                .filter(pizza->pizza.getIngredients().stream().anyMatch(Ingredients::isMeat))
                .sorted((Pizza p1, Pizza p2)->(int) p2.getIngredients().stream().filter(Ingredients::isMeat).count() - (int)
                        p1.getIngredients().stream().filter(Ingredients::isMeat).count())
                .collect(Collectors.toList());
        System.out.println(p);
        return p;

    }

    public static Map<Integer,List<Pizza>> groupByPrice(){
        Map<Integer,List<Pizza>> p=Arrays.stream(Pizza.values())
                .collect(Collectors.groupingBy(pizza->pizza.getIngredients().stream().mapToInt(Ingredients::getPrice).sum()));
        System.out.println(p);
        return p;
    }

    public static void formatedMenu() {
        Arrays.stream(Pizza.values())
                .forEach(pizza -> System.out.println(pizza.getName() + " : " + pizza.getIngredients().toString() + " : " + pizza.getIngredients().stream().mapToInt(Ingredients::getPrice).sum()));

    }

}