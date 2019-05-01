package controller;

import classes.menu.Pizza;
import classes.operations.FieldOperations;
import classes.storage.Item;
import classes.waiting_room.Customer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.lang.reflect.Field;
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
    public ListView class_fields_list;
    public Text fields_list_text;
    public Text field_field_name_text;
    public TextField field_field_name_textfield;
    public Text field_set_value_text;
    public TextField field_set_value_textfield;
    public Button field_set_field_value_button;
    public TextField field_field_answer_textfield;
    public Text field_field_answer_text;
    public Text field_field_type_list_text;

    private static Object chosenClassObject = null;
    private FieldOperations FO = new FieldOperations();

    public void initialize() {
        showAllClasses();
    }

    private void showAllClasses() {
        Class reflectPizza = Pizza.class;
        Class reflectItem = Item.class;
        Class reflectCustomer = Customer.class;

        class_methods_textfield.setText("Available classes:");
        class_methods_textarea.setText(
                reflectPizza.getName() + "\n" +
                        reflectItem.getName() + "\n" +
                        reflectCustomer.getName()
        );
    }

    public void useClass(ActionEvent event) {
        String className = class_name_field.getText();
        createObjectOfClass(className);
    }

    public void invokeMethod(ActionEvent event) throws InvocationTargetException, IllegalAccessException {
        String methodName = "";
        methodName = method_name_field.getText();
        Method chosenMethod = null;
        try {
            chosenMethod = chosenClassObject.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            method_answer_field.setText("You used wrong method or method has no answer.");
        }

        if (chosenMethod != null) {
            method_answer_field.setText((String) chosenMethod.invoke(chosenClassObject));
        }
    }

    public void setFieldValue(ActionEvent event) {
        Field chosenField = null;
        String fieldName = field_field_name_textfield.getText();
        String newFieldValue = field_set_value_textfield.getText();

        try {
            chosenField = chosenClassObject.getClass().getDeclaredField(fieldName);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (chosenField != null) {
            if (chosenField.getType().isEnum()) {
                FO.callEnum(chosenField, newFieldValue);
                 field_field_answer_textfield.setText(FO.getAnswer());
            } else if (String.class.equals(chosenField.getType())) {
                FO.callString(chosenField, newFieldValue);
                 field_field_answer_textfield.setText(FO.getAnswer());
            } else if (int.class.equals(chosenField.getType())) {
                FO.callInt(chosenField, newFieldValue);
                 field_field_answer_textfield.setText(FO.getAnswer());
            }
        } else
            field_field_answer_textfield.setText("Wrong field chosen.");
    }

    private void showFieldFields() {
        fields_list_text.setVisible(true);
        class_fields_list.setVisible(true);
        field_field_name_text.setVisible(true);
        field_field_name_textfield.setVisible(true);
        field_set_value_text.setVisible(true);
        field_set_value_textfield.setVisible(true);
        field_set_field_value_button.setVisible(true);
        field_field_answer_text.setVisible(true);
        field_field_answer_textfield.setVisible(true);
        field_field_type_list_text.setVisible(true);
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
            showFieldFields();
            showAllClassFields();
            FO.getObject();
        } else class_methods_textfield.setText("Wrong class name!");

    }

    private void showAllClassFields() {
        Field[] classFields = chosenClassObject.getClass().getDeclaredFields();
        class_fields_list.getItems().clear();

        for (Field f : classFields) {
            class_fields_list.getItems().add(f.getName() + " : " + f.getType());
        }
    }

    private void showAllMethods(Method[] methodsOfChosenClass) {
        StringBuilder allMethods = new StringBuilder();
        StringBuilder getterMethods = new StringBuilder();
        StringBuilder setterMethods = new StringBuilder();
        StringBuilder privateMethods = new StringBuilder();

        for (Method m : methodsOfChosenClass) {
            if (m.getName().contains("get"))
                getterMethods.append(m.getName()).append("\n");
            else if (m.getName().contains("set"))
                setterMethods.append(m.getName()).append("\n");
            else if (m.getName().contains("is") || m.getName().contains("are"))
                privateMethods.append(m.getName()).append("\n");
            else
                allMethods.append(m.getName()).append("\n");
        }

        class_methods_textfield.setText("Available methods:");
        class_methods_textarea.setText(allMethods.toString());
    }

    public static Object getChosenClassObject() {
        return chosenClassObject;
    }

    public static void setChosenClassObject(Object chosenClassObject) {
        ReflectionController.chosenClassObject = chosenClassObject;
    }


}
