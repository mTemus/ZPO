package controller;

import classes.menu.Pizza;
import classes.storage.Item;
import classes.waiting_room.Customer;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReflectionController {


    public TextField class_name_field;
    public TextArea class_methods_textarea;
    public TextField class_methods_textfield;


    public void initialize() {
        showAllClasses();
    }

    public void showAllClasses() {

        Class reflectPizza = Pizza.class;
        Class reflectItem = Item.class;
        Class reflectCustomer = Customer.class;

        class_methods_textfield.setText("Available classes:");
        class_methods_textarea.setText(
                reflectPizza.getName() + "\n" +
                reflectItem.getName() + "\n" +
                reflectCustomer.getName());


    }


}
