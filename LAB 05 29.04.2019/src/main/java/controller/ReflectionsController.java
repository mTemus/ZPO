package controller;

import beanClasses.UserBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class ReflectionsController {
    public TableView class_fields_tableView;
    public TableColumn col_object_id;
    public TableColumn col_field_name;
    public TableColumn col_field_type;
    public TableColumn col_field_actual_value;
    public TextArea classes_class_fields_textarea;
    public Button classes_chose_other_clas_buttons;
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
    private Object actualObject;
    private int objectsQuantity = 1;
    private int objectsIDX = objectsQuantity - 1;
    private int objectsI;

    ObservableList<UserBean> users = FXCollections.observableArrayList();


    public void choseOtherClass(ActionEvent event) {
    }

    public void createNewClassObject(ActionEvent event) {
    }

    public void useObjectById(ActionEvent event) {
    }

    public void deleteObjectById(ActionEvent event) {
    }
}
