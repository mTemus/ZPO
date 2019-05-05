package operations;

import java.lang.reflect.Method;

public class MethodOperations {

    public StringBuilder getAllMethods(Method[] methods) {
        StringBuilder allMethods = new StringBuilder();

        for (Method m : methods) {
            allMethods.append(m.getName()).append("\n");
        }
        return allMethods;
    }
}
