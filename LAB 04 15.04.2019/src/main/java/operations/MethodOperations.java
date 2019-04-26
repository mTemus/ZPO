package operations;

import controller.ReflectionController;

import java.lang.reflect.Method;

public class MethodOperations {
    ReflectionController RC = new ReflectionController();


    void showAllMethods(Method[] methodsOfChosenClass) {
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

        RC.class_methods_textfield.setText("Available methods:");
        RC.class_methods_textarea.setText(allMethods.toString());
    }

}
