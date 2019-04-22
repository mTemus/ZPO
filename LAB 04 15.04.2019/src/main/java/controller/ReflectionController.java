package controller;

import classes.menu.Pizza;
import classes.storage.Item;
import classes.waiting_room.Customer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ReflectionController {


    public TextField class_name_field;
    public ComboBox classes_combobox;


    public void initialize(){
        showAllClasses();
    }

    public void showAllClasses() {

        Class reflectPizza = Pizza.class;
        Class reflectItem = Item.class;
        Class reflectCustomer = Customer.class;

        classes_combobox.getItems().addAll(
                reflectPizza.getName(),
                reflectItem.getName(),
                reflectCustomer.getName()
        );



    }


}
