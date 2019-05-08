package operations;

import beanClasses.ItemBean;
import beanClasses.PizzaBean;
import beanClasses.UserBean;
import controller.ReflectionsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ObjectOperations {

    private static ObservableList<UserBean> users = FXCollections.observableArrayList();
    private static ObservableList<PizzaBean> pizzas = FXCollections.observableArrayList();
    private static ObservableList<ItemBean> items = FXCollections.observableArrayList();

    private static List deletedIDs = new ArrayList();
    private static int createdObjects = 0;

    public void createObjectOfClass(String className, int idx) {
        switch (className) {
            case "beanClasses.ItemBean":
                createItem(className, idx);
                break;
            case "beanClasses.PizzaBean":
                createPizza(className, idx);
                break;
            case "beanClasses.UserBean":
                createUser(className, idx);
                break;
            default:
                System.out.println("Creating class object error by class name");
                break;
        }
    }

    private void createItem(String className, int idx) {
        items = ReflectionsController.getItems();
        try {
            items.add((ItemBean) Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isDeletedIDLess(idx)) {
            items.get(getCreatedObjects()).setObjectID(Integer.parseInt(deletedIDs.get(0).toString()));
            deletedIDs.remove(0);
        } else
            items.get(getCreatedObjects()).setObjectID(idx++);

        setCreatedObjects(getCreatedObjects() + 1);
    }

    private void createPizza(String className, int idx) {
        pizzas = ReflectionsController.getPizzas();
        try {
            pizzas.add((PizzaBean) Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (isDeletedIDLess(idx)) {
            pizzas.get(getCreatedObjects()).setObjectID(Integer.parseInt(deletedIDs.get(0).toString()));
            deletedIDs.remove(0);
        } else
            pizzas.get(getCreatedObjects()).setObjectID(idx++);
        setCreatedObjects(getCreatedObjects() + 1);
    }

    private void createUser(String className, int idx) {
        users = ReflectionsController.getUsers();
        try {
            users.add((UserBean) Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (isDeletedIDLess(idx)) {
            users.get(getCreatedObjects()).setObjectID(Integer.parseInt(deletedIDs.get(0).toString()));
            deletedIDs.remove(0);
        } else
            users.get(getCreatedObjects()).setObjectID(idx++);
        setCreatedObjects(getCreatedObjects() + 1);
    }

    private void addDeletedIDtoArray(int id) {
        deletedIDs.add(id);
    }

    private boolean isDeletedIDLess(int id) {
        boolean state = false;

        if (deletedIDs.size() > 0)
            for (Object deletedID : deletedIDs) {
                if (Integer.parseInt(deletedID.toString()) < id) {
                    state = true;
                    break;
                }
            }
        return state;
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
        addDeletedIDtoArray(id);
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

    public int getCreatedObjects() {
        return createdObjects;
    }

    public void setCreatedObjects(int createdObjects) {
        ObjectOperations.createdObjects = createdObjects;
    }

}
