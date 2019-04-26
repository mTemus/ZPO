package operations;

import classes.menu.Pizza;
import classes.storage.Item;
import classes.waiting_room.Customer;
import controller.ReflectionController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassOperations {

    ReflectionController RC = new ReflectionController();
    MethodOperations MO = new MethodOperations();

    public void showAllClasses() {
        Class reflectPizza = Pizza.class;
        Class reflectItem = Item.class;
        Class reflectCustomer = Customer.class;

        RC.class_methods_textfield.setText("Available classes:");
        RC.class_methods_textarea.setText(
                reflectPizza.getName() + "\n" +
                        reflectItem.getName() + "\n" +
                        reflectCustomer.getName()
        );
    }

    public void createObjectOfClass(String className) {
        try {
            RC.setChosenClassObject(Class.forName(className).newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (RC.getChosenClassObject() != null) {
            Method[] methodsOfChosenClass = RC.getChosenClassObject().getClass().getDeclaredMethods();
            MO.showAllMethods(methodsOfChosenClass);
            RC.showFieldFields();
            showAllClassFields();
        } else RC.class_methods_textfield.setText("Wrong class name!");


    }

    private void showAllClassFields() {
        Field[] classFields = RC.getChosenClassObject().getClass().getDeclaredFields();
        for (Field f : classFields) {
            RC.class_fields_list.getItems().add(f.getName() + " : " + f.getType());
        }
    }
}
