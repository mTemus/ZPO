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


    public void choseOtherClass(ActionEvent event) throws IOException {
        users.clear();
        items.clear();
        pizzas.clear();
        className = "";
        objectsQuantity = 0;
//        objectsIDX = 0;

        SO.changeSceneToLoadClass(event);
    }

    public void createNewClassObject(ActionEvent event) {
    }

    public void useObjectById(ActionEvent event) {
    }

    public void deleteObjectById(ActionEvent event) {
    }

    public void initialize() {
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

        System.out.println(users);
        System.out.println(items);
        System.out.println(pizzas);

    }

    private void updateIDX() {
        ++objectsQuantity;
        objectsIDX = objectsQuantity - 1;
    }

    private void initializeFields() {
        Object chosenClassObject = users.get(objectsIDX);

        Field[] fields = chosenClassObject.getClass().getDeclaredFields();
        Method[] methods = chosenClassObject.getClass().getDeclaredMethods();

        StringBuilder allMethods = MO.getAllMethods(methods);
        StringBuilder allFields = FO.getAllFields(fields);

        classes_class_name_text.setText(chosenClassObject.getClass().getName());
        classes_class_fields_textarea.setText(allFields.toString());
        classes_class_methods_textarea.setText(allMethods.toString());
    }


}
