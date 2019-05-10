package controller;

import operations.NamedAnnotationOperations;
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
    public TextField classes_new_value_textfield;
    public Button classes_invoke_setter_button;
    public TextField classes_current_value_textfield;
    public Button classes_invoke_getter_button;
    public Button classes_delete_object_button;
    public TextArea classes_class_methods_textarea;
    public TextField classes_error_field_textfield;
    public Text classes_class_name_text;

    private static int objectsQuantity = 0;
    private static int objectsIDX = 0;
    private static int objectsI;
    private static String className;
    public Button annotation_change_column_name_button;
    public TextField annotations_new_column_name_textfield;

    private ObjectOperations OO = new ObjectOperations();
    private FieldOperations FO = new FieldOperations();
    private MethodOperations MO = new MethodOperations();
    private StageOperations SO = new StageOperations();
    private NamedAnnotationOperations NAO = new NamedAnnotationOperations();

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


    public void changeColumnName(ActionEvent event) {
        col_field_name.setText(NAO.getName());
        classes_error_field_textfield.setText("Column header changed successfully.");
    }

    public void createNewClassObject(ActionEvent event) {
        OO.createObjectOfClass(className, objectsIDX);
        updateIDX();
        updateListsAfterOO();
        try {
            overwriteNewObject();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        clearFields();
    }

    public void useObjectById(ActionEvent event) {
        int pointerI = Integer.parseInt(classes_enter_object_id_field.getText());
        if (pointerI <= objectsIDX) {
            objectsI = pointerI;
            classes_current_object_id_field.setText("Object id: " + objectsI);
            classes_error_field_textfield.clear();
            try {
                overwriteNewObject();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            classes_error_field_textfield.setText("Wrong object id. There is no such ID, last ID is: " + objectsIDX);
        }
        clearFields();

    }

    public void deleteObjectById(ActionEvent event) {
        if (objectsIDX > objectsI)
            --objectsIDX;

        OO.deleteObjectOfClass(className, objectsI);
        updateListsAfterOO();
        --objectsQuantity;
        classes_current_object_id_field.clear();
        classes_enter_object_id_field.clear();
        classes_error_field_textfield.setText("Deleting successful, please load new object.");

    }

    public void invokeSetter(ActionEvent event) {
        setFieldValue();
        overwriteObject();
    }

    public void invokeGetter(ActionEvent event) {
        getFieldValue();
    }

    public void initialize() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        className = LoadClassController.getChosenClassName();
        updateIDX();
        OO.createObjectOfClass(className, objectsIDX);
        updateListsAfterOO();
        initializeFields();
    }

    private void updateListsAfterOO() {
        users = ObjectOperations.getUsers();
        items = ObjectOperations.getItems();
        pizzas = ObjectOperations.getPizzas();
    }

    private void updateIDX() {
        ++objectsQuantity;
        objectsIDX = objectsQuantity - 1;
        objectsI = objectsIDX;
        classes_current_object_id_field.setText("Object id: " + objectsIDX);
    }

    private void initializeFields() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        setChosenClassObject(Class.forName(className).newInstance());

        Field[] fields = getChosenClassObject().getClass().getDeclaredFields();
        Method[] methods = getChosenClassObject().getClass().getDeclaredMethods();

        StringBuilder allMethods = MO.getAllMethods(methods);
        StringBuilder allFields = FO.getAllFields(fields);

        classes_class_name_text.setText(getChosenClassObject().getClass().getName());
        classes_class_fields_textarea.setText(allFields.toString());
        classes_class_methods_textarea.setText(allMethods.toString());
    }

    private void setFieldValue() {
        Field chosenField = null;
        String fieldName = classes_field_name_textfield.getText();
        String newFieldValue = classes_new_value_textfield.getText();

        if (fieldName != null && newFieldValue != null) {
            try {
                chosenField = getChosenClassObject().getClass().getDeclaredField(fieldName);
                System.out.println(getChosenClassObject().toString());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            if (chosenField != null) {
                Method setter = MO.lookForMethod(getChosenClassObject(), chosenField, "set");

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

    private void getFieldValue() {
        Field chosenField = null;
        String fieldName = classes_field_name_textfield.getText();
        String answer = "";

        if (fieldName != null) {
            try {
                chosenField = getChosenClassObject().getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            if (chosenField != null) {
                Method getter = MO.lookForMethod(getChosenClassObject(), chosenField, "get");
                if (chosenField.getType().isEnum()) {
                    answer = FO.callEnumGetter(getter);
                } else if (String.class.equals(chosenField.getType())) {
                    answer = FO.callStringGetter(getter);
                } else if (int.class.equals(chosenField.getType())) {
                    answer = FO.callIntGetter(getter);
                } else if (LocalDate.class.equals(chosenField.getType())) {
                    answer = FO.callLocalDataSGetter(getter);
                }
                classes_current_value_textfield.setText(answer);
            } else
                classes_error_field_textfield.setText("You chose wrong field.");
        } else
            classes_error_field_textfield.setText("Please write field name correctly.");

    }

    private void overwriteObject() {
        setChosenClassObject(FieldOperations.getChosenClassObject());

        if (getChosenClassObject().getClass().equals(UserBean.class))
            users.set(objectsI, (UserBean) getChosenClassObject());
        else if (getChosenClassObject().getClass().equals(ItemBean.class))
            items.set(objectsI, (ItemBean) getChosenClassObject());
        else if (getChosenClassObject().getClass().equals(PizzaBean.class))
            pizzas.set(objectsI, (PizzaBean) getChosenClassObject());
    }

    private void overwriteNewObject() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object tmpObject = Class.forName(className).newInstance();

        if (tmpObject.getClass().equals(ItemBean.class))
            setChosenClassObject(items.get(objectsI));
        else if (tmpObject.getClass().equals(UserBean.class))
            setChosenClassObject(users.get(objectsI));
        else if (tmpObject.getClass().equals(PizzaBean.class))
            setChosenClassObject(pizzas.get(objectsI));
    }

    private void clearFields() {
        classes_field_name_textfield.clear();
        classes_current_value_textfield.clear();
        classes_new_value_textfield.clear();
    }

//    private void setTableItems(ObservableList<UserBean> usersList) {
////            col_id_user.setCellValueFactory(new PropertyValueFactory<User, Long>("id"));
////            col_name_user.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
////            col_surname_user.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
////            col_login_user.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
////            col_password_user.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
////            col_email_user.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
////            col_date_user.setCellValueFactory(new PropertyValueFactory<User, String>("date"));
////            tbl_users.setItems(usersList);
//
//            col_object_id.setCellValueFactory(new PropertyValueFactory<UserBean, Integer>("ObjectID"));
//            col_field_name.setCellValueFactory(new PropertyValueFactory<UserBean, String>(Arrays.toString(usersList.getClass().getDeclaredFields())));
////            col_field_actual_value.setCellValueFactory(new PropertyValueFactory<UserBean, String>(usersList.getClass().getDeclaredFields()instanceof UserBean));
//            class_fields_tableView.setItems(usersList);
//
//    }


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
