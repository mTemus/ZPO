package controller;

import beanClasses.ItemBean;
import beanClasses.PizzaBean;
import beanClasses.UserBean;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import operations.StageOperations;

import java.io.IOException;

public class LoadClassController {
    public ComboBox classes_combobox;
    public Button classes_button;

    private static String chosenClassName;
    private StageOperations SO = new StageOperations();

    public void takeThisClass(ActionEvent event) {
        chosenClassName = (String) classes_combobox.getValue();
    }

    public void chooseThisClass(ActionEvent event) {
        if (getChosenClassName() != null) {
            try {
                SO.changeSceneToReflections(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize() {
        showAllClasses();
    }

    private void showAllClasses() {
        classes_combobox.getItems().addAll(ItemBean.class.getName(), PizzaBean.class.getName(), UserBean.class.getName());
    }

    public static String getChosenClassName() {
        return chosenClassName;
    }

}
