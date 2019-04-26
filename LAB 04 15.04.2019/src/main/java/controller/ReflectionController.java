package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import operations.ClassOperations;
import operations.FieldOperations;
import operations.MethodOperations;

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
    public static TextField field_field_answer_textfield;
    public Text field_field_answer_text;
    public Text field_field_type_list_text;


    private static Object chosenClassObject = null;
    private FieldOperations FO = new FieldOperations();
    private ClassOperations CO = new ClassOperations();
    private MethodOperations MO = new MethodOperations();

    public void initialize() {
        CO.showAllClasses();
    }

    public void useClass(ActionEvent event) {
        String className = class_name_field.getText();
        CO.createObjectOfClass(className);
        FO.setChosenClassObject(chosenClassObject);

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
                FO.callEnum(chosenField, newFieldValue);
            } else if (String.class.equals(chosenField.getType())) {
                FO.callString(chosenField, newFieldValue);
            } else if (int.class.equals(chosenField.getType())) {
                FO.callInt(chosenField, newFieldValue);
            }
        } else
            field_field_answer_textfield.setText("Wrong field chosen.");
    }

    public void showFieldFields() {
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

    public Object getChosenClassObject() {
        return chosenClassObject;
    }

    public void setChosenClassObject(Object chosenClassObject) {
        ReflectionController.chosenClassObject = chosenClassObject;
    }
}
