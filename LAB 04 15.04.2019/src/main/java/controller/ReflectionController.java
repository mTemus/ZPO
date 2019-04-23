package controller;

import classes.menu.Pizza;
import classes.storage.Item;
import classes.waiting_room.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionController {


    public TextField class_name_field;
    public TextArea class_methods_textarea;
    public TextField class_methods_textfield;
    public Button use_class_button;
    public TextField method_name_field;
    public Button invoke_method_button;
    public TextField method_answer_field;

    private Class reflectPizza = Pizza.class;
    private Class reflectItem = Item.class;
    private Class reflectCustomer = Customer.class;

    private Object chosenClassObject = null;

    Method[] pizzaMethods = reflectPizza.getMethods();
    Method[] itemMethods = reflectItem.getMethods();
    Method[] customerMethods = reflectCustomer.getMethods();


    public void useClass(ActionEvent event) {
        String className = class_name_field.getText();
        createObjectOfClass(className);
    }

    public void invokeMethod(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "";
        String methodAnswer = "";
        methodName = method_name_field.getText();
        System.out.println(methodName);
        Method chosenMethod = chosenClassObject.getClass().getMethod(methodName);

        if (chosenMethod != null){
            methodAnswer = (String) chosenMethod.invoke(chosenClassObject);
            method_answer_field.setText(methodAnswer);
        } else
            method_answer_field.setText("You used wrong method.");

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
            chosenClassObject = Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (chosenClassObject != null) {
            Method[] methodsOfChosenClass = chosenClassObject.getClass().getDeclaredMethods();
            showAllMethods(methodsOfChosenClass);
        } else class_methods_textfield.setText("Wrong class name!");


    }

    public void showAllClassFields() {

    }

    private void showAllMethods(Method[] methodsOfChosenClass) {
        StringBuilder allMethods = new StringBuilder();
        StringBuilder getterMethods = new StringBuilder();
        StringBuilder setterMethods = new StringBuilder();

        for (Method m : methodsOfChosenClass) {
            if (m.getName().contains("get"))
                getterMethods.append(m.getName()).append("\n");
            else if (m.getName().contains("set"))
                setterMethods.append(m.getName()).append("\n");
            else
                allMethods.append(m.getName()).append("\n");
        }


        class_methods_textfield.setText("Available methods:");
        class_methods_textarea.setText(allMethods.toString());
    }


}
