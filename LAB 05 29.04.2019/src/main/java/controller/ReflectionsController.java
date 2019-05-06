package controller;

import beanClasses.ItemBean;
import beanClasses.PizzaBean;
import beanClasses.UserBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import operations.FieldOperations;
import operations.MethodOperations;
import operations.ObjectOperations;
import operations.StageOperations;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class ReflectionsController {
    public TableView class_fields_tableView;
    public TableColumn col_object_id;
    public TableColumn col_field_name;
    public TableColumn col_field_type;
    public TableColumn col_field_actual_value;
    public TextArea classes_class_fields_textarea;
    public Button classes_chose_other_class_button;
    public Button classes_create_new_object_button;
    public TextField classes_current_object_id_field;
    public TextField classes_enter_object_id_field;
    public Button classes_use_object_button;
    public TextField classes_field_name_textfield;
    public PasswordField classes_new_value_textfield;
    public Button classes_invoke_setter_button;
    public TextField classes_current_value_textfield;
    public Button classes_invoke_getter_button;
    public Button classes_delete_object_button;
    public TextArea classes_class_methods_textarea;
    public TextField classes_error_field_textfield;
    public Text classes_class_name_text;

    private static int objectsQuantity = 0;
    private static int objectsIDX;
    private static int objectsI;
    private static String className;

    private ObjectOperations OO = new ObjectOperations();
    private FieldOperations FO = new FieldOperations();
    private MethodOperations MO = new MethodOperations();
    private StageOperations SO = new StageOperations();

    private static ObservableList<UserBean> users = FXCollections.observableArrayList();
    private static ObservableList<PizzaBean> pizzas = FXCollections.observableArrayList();
    private static ObservableList<ItemBean> items = FXCollections.observableArrayList();

    private static Object chosenClassObject;


    public void choseOtherClass(ActionEvent event) throws IOException {
        users.clear();
        items.clear();
        pizzas.clear();
        className = "";
        objectsQuantity = 0;
        objectsIDX = 0;

        SO.changeSceneToLoadClass(event);
    }

    public void createNewClassObject(ActionEvent event) {
        OO.createObjectOfClass(className);
        updateIDX();
        updateLists();
    }

    public void useObjectById(ActionEvent event) {
        int pointerI = Integer.parseInt(classes_enter_object_id_field.getText());
        if (pointerI <= objectsIDX) {
            objectsI = pointerI;
            classes_current_object_id_field.setText("Object id: " + objectsI);
            classes_error_field_textfield.clear();
        } else {
            classes_error_field_textfield.setText("Wrong object id. There is no such ID, last ID is: " + objectsIDX);
        }

    }

    public void deleteObjectById(ActionEvent event) {
        if (objectsIDX > objectsI)
            --objectsIDX;

        OO.deleteObjectOfClass(className, objectsI);
        updateLists();
        --objectsQuantity;
        classes_current_object_id_field.clear();
        classes_enter_object_id_field.clear();
        classes_error_field_textfield.setText("Deleting successful, please load new object.");

    }

    public void initialize() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        className = LoadClassController.getChosenClassName();
        updateIDX();
        OO.createObjectOfClass(className);
        updateLists();
        initializeFields();
    }

    private void updateLists() {
        users = ObjectOperations.getUsers();
        items = ObjectOperations.getItems();
        pizzas = ObjectOperations.getPizzas();
    }

    private void updateIDX() {
        ++objectsQuantity;
        objectsIDX = objectsQuantity - 1;
        classes_current_object_id_field.setText("Object id: " + objectsIDX);
    }

    private void initializeFields() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        chosenClassObject = Class.forName(className).newInstance();

        Field[] fields = chosenClassObject.getClass().getDeclaredFields();
        Method[] methods = chosenClassObject.getClass().getDeclaredMethods();

        StringBuilder allMethods = MO.getAllMethods(methods);
        StringBuilder allFields = FO.getAllFields(fields);

        classes_class_name_text.setText(chosenClassObject.getClass().getName());
        classes_class_fields_textarea.setText(allFields.toString());
        classes_class_methods_textarea.setText(allMethods.toString());
    }

    public void setFieldValue() {
        Field chosenField = null;
        String fieldName = classes_field_name_textfield.getText();
        String newFieldValue = classes_new_value_textfield.getText();

        if (fieldName != null && newFieldValue != null) {
            try {
                chosenField = chosenClassObject.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            if (chosenField != null) {
                Method setter = MO.lookForMethod(chosenClassObject, chosenField, "set");

                if (chosenField.getType().isEnum()) {
                    Type enumToConvertOn = FO.lookForEnum(chosenField);
                    FO.callEnumSetter(setter, newFieldValue, enumToConvertOn);
                } else if (String.class.equals(chosenField.getType())) {
                    FO.callStringSetter(setter, newFieldValue);
                } else if (int.class.equals(chosenField.getType())) {
                    FO.callIntSetter(setter, newFieldValue);
                } else if (LocalDate.class.equals(chosenField.getType())) {
                    FO.callLocalDataSetter(setter, newFieldValue);
                }
            } else
                classes_error_field_textfield.setText("You chose wrong field.");
        } else
            classes_error_field_textfield.setText("Please write field name and it value correctly.");


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

    public static Object getChosenClassObject() {
        return chosenClassObject;
    }

    public static void setChosenClassObject(Object chosenClassObject) {
        ReflectionsController.chosenClassObject = chosenClassObject;
    }
}
