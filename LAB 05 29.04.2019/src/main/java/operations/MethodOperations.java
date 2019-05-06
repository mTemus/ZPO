package operations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MethodOperations {

    public StringBuilder getAllMethods(Method[] methods) {
        StringBuilder allMethods = new StringBuilder();

        for (Method m : methods) {
            allMethods.append(m.getName()).append("\n");
        }
        return allMethods;
    }

    public Method lookForMethod(Object object, Field field, String fieldPrefix){
        Method method = null;

        String fieldName = field.getName();
        String setterName;
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        setterName = fieldPrefix + fieldName;

        for (Method m : object.getClass().getDeclaredMethods()) {
            String methodName = m.getName();
            if (methodName.equals(setterName)) {
                method = m;
                break;
            }
        }
        return method;
    }

}
