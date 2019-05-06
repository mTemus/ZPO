package operations;

import beanClasses.ItemBean;
import beanClasses.PizzaBean;
import beanClasses.UserBean;
import controller.ReflectionsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Ref;

public class ObjectOperations {

    private static ObservableList<UserBean> users = FXCollections.observableArrayList();
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
        items = ReflectionsController.getItems();
        try {
            items.add((ItemBean) Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createPizza(String className) {
        pizzas = ReflectionsController.getPizzas();
        try {
            pizzas.add((PizzaBean) Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createUser(String className) {
        users = ReflectionsController.getUsers();
        try {
            users.add((UserBean) Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteObjectOfClass(String className, int id) {
        switch (className) {
            case "beanClasses.ItemBean":
                deleteItem(id);
                break;
            case "beanClasses.PizzaBean":
                deletePizza(id);
                break;
            case "beanClasses.UserBean":
                deleteUser(id);
                break;
            default:
                System.out.println("Creating class object error by class name");
                break;
        }
    }

    private void deleteItem(int id) {
        items = ReflectionsController.getItems();
        items.remove(id);
    }

    private void deletePizza(int id) {
        pizzas = ReflectionsController.getPizzas();
        pizzas.remove(id);
    }

    private void deleteUser(int id) {
        users = ReflectionsController.getUsers();
        users.remove(id);
    }

    public static ObservableList<UserBean> getUsers() {
        return users;
    }

    public static ObservableList<PizzaBean> getPizzas() {
        return pizzas;
    }

    public static ObservableList<ItemBean> getItems() {
        return items;
    }
}
