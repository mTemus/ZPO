package controller;

import classes.menu.Pizza;
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
import java.lang.reflect.Type;

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
        String methodAnswer = "";
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

    public void setFieldValue(ActionEvent event) throws IllegalAccessException, InvocationTargetException {
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
                callEnum(chosenField, newFieldValue);
            } else if (String.class.equals(chosenField.getType())) {
                callString(chosenField, newFieldValue);
            } else if (int.class.equals(chosenField.getType())) {
                callInt(chosenField, newFieldValue);
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
        } else class_methods_textfield.setText("Wrong class name!");
        
    }

    private void showAllClassFields() {
        Field[] classFields = chosenClassObject.getClass().getDeclaredFields();
        for (Field f : classFields) {
            class_fields_list.getItems().add(f.getName() + " : " + f.getType());
        }
    }

    private Method lookForSetter(Field field) {
        Method setter = null;
        String fieldName = field.getName();
        String setterName;
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        setterName = "set" + fieldName;

        for (Method m : chosenClassObject.getClass().getDeclaredMethods()) {
            String methodName = m.getName();
            if (methodName.equals(setterName)) {
                setter = m;
                break;
            }
        }
        return setter;
    }

    private Method lookForGetter(Field field) {
        Method getter = null;
        String fieldName = field.getName();
        String getterName;
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        getterName = "get" + fieldName;
        for (Method m : chosenClassObject.getClass().getDeclaredMethods()) {
            String methodName = m.getName();
            if (methodName.equals(getterName)) {
                getter = m;
                break;
            }
        }
        return getter;
    }

    private void callEnum(Field field, String newValue) {
        Method setter = lookForSetter(field);
        Method getter = lookForGetter(field);
        Type chosenEnum = lookForEnum(field);

        callEnumSetter(setter, newValue, chosenEnum);
        callEnumGetter(getter);
    }

    private Type lookForEnum(Field field) {
        Type chosenEnum = null;
        String fieldName = field.getName();

        for (Field f : chosenClassObject.getClass().getDeclaredFields()) {
            if (f.getName().equals(fieldName))
                chosenEnum = f.getType();
        }


        return chosenEnum;
    }

    private void callEnumSetter(Method setter, String newValue, Type chosenEnum) {
        try {
            setter.invoke(chosenClassObject, Enum.valueOf((Class<Enum>) chosenEnum, newValue));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void callEnumGetter(Method getter) {
        try {
            field_field_answer_textfield.setText("New field value: " + getter.invoke(chosenClassObject));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void callString(Field field, String newValue) {
        Method setter = lookForSetter(field);
        Method getter = lookForGetter(field);

        callStringSetter(setter, newValue);
        callStringGetter(getter);

    }

    private void callStringSetter(Method setter, String newValue) {
        try {
            setter.invoke(chosenClassObject, newValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void callStringGetter(Method getter) {
        try {
            String answer = (String) getter.invoke(chosenClassObject);
            field_field_answer_textfield.setText("New field value: " + answer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void callInt(Field field, String newValue) {
        Method setter = lookForSetter(field);
        Method getter = lookForGetter(field);

        callIntSetter(setter, newValue);
        callIntGetter(getter);

    }

    private void callIntSetter(Method setter, String newValue) {
        try {
            setter.invoke(chosenClassObject, Integer.parseInt(newValue));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void callIntGetter(Method getter) {
        try {
            field_field_answer_textfield.setText("New field value: " + getter.invoke(chosenClassObject));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
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

}
