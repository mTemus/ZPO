package operations;

import beanClasses.ItemBean;
import beanClasses.PizzaBean;
import beanClasses.UserBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObjectOperations {

    public static ObservableList<UserBean> users = FXCollections.observableArrayList();
    private static ObservableList<PizzaBean> pizzas = FXCollections.observableArrayList();
    private static ObservableList<ItemBean> items = FXCollections.observableArrayList();

    public void createObjectOfClass(String className) {
        switch (className) {
            case "beanClasses.ItemBean":
                createItem(className);
                break;
            case "beanClasses.PizzaBean":
                createPizza(className);
                break;
            case "beanClasses.UserBean":
                createUser(className);
                break;
            default:
                System.out.println("Creating class object error by class name");
                break;
        }

    }

    private void createItem(String className) {
        try {
            items.add((ItemBean) Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createPizza(String className) {
        try {
            pizzas.add((PizzaBean) Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createUser(String className) {
        try {
            users.add((UserBean) Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(users);
    }

}
