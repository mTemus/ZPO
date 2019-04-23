package controller;

import classes.menu.Pizza;
import classes.storage.Item;
import classes.waiting_room.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.lang.reflect.Method;

public class ReflectionController {


    public TextField class_name_field;
    public TextArea class_methods_textarea;
    public TextField class_methods_textfield;
    public Button use_class_button;

    private Class reflectPizza = Pizza.class;
    private Class reflectItem = Item.class;
    private Class reflectCustomer = Customer.class;

    private Object chosedClassObject = null;

    Method[] pizzaMethods = reflectPizza.getMethods();
    Method[] itemMethods = reflectItem.getMethods();
    Method[] customerMethods = reflectCustomer.getMethods();


    public void useClass(ActionEvent event) {
        String className = class_name_field.getText();
        createObjectOfClass(className);
    }

    public void initialize() {
        showAllClasses();
    }

    private void showAllClasses() {
        class_methods_textfield.setText("Available classes:");
        class_methods_textarea.setText(
                reflectPizza.getName() + "\n" +
                        reflectItem.getName() + "\n" +
                        reflectCustomer.getName()
        );
    }

    private void createObjectOfClass(String className) {
        try {
            chosedClassObject = Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (chosedClassObject != null) {
            Method[] methodsOfChosenClass = chosedClassObject.getClass().getDeclaredMethods();
            showAllMethods(methodsOfChosenClass);
        } else class_methods_textfield.setText("Wrong class name!");


    }

    public void showAllClassFields() {

    }

    private void showAllMethods(Method[] methodsOfChosenClass) {
        StringBuilder allMethods = new StringBuilder();

        for (Method m : methodsOfChosenClass) {

            System.out.println(m.getName().contains("get"));

            if (m.getName().contains("get") || m.getName().contains("set")) {
            } else
                allMethods.append(m.getName()).append("\n");


        }


        class_methods_textfield.setText("Available methods:");
        class_methods_textarea.setText(allMethods.toString());
    }


}
