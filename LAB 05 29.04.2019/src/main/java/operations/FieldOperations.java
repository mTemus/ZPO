package operations;

import controller.ReflectionsController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class FieldOperations {

    public StringBuilder getAllFields(Field[] fields) {
        StringBuilder allFields = new StringBuilder();

        for (Field f : fields) {
            allFields.append(f.getName()).append(" : ").append(f.getType()).append("\n");
        }
        return allFields;
    }

    private static Object chosenClassObject;

    private static String answer;

    public void getTheChosen() {
        setChosenClassObject(ReflectionsController.getChosenClassObject());
    }

    private Method lookForSetter(Field field) {
        getTheChosen();
        Method setter = null;
        String fieldName = field.getName();
        String setterName;
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        setterName = "set" + fieldName;

        for (Method m : chosenClassObject.getClass().getDeclaredMethods()) {
            String methodName = m.getName();
            if (methodName.equals(setterName)) {
                setter = m;
                break;
            }
        }
        return setter;
    }

    public Method lookForGetter(Field field) {
        Method getter = null;
        String fieldName = field.getName();
        String getterName;
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        getterName = "get" + fieldName;
        for (Method m : chosenClassObject.getClass().getDeclaredMethods()) {
            String methodName = m.getName();
            if (methodName.equals(getterName)) {
                getter = m;
                break;
            }
        }
        return getter;
    }

    public void callEnum(Field field, String newValue) {
        Method setter = lookForSetter(field);
        Method getter = lookForGetter(field);
        Type chosenEnum = lookForEnum(field);

        callEnumSetter(setter, newValue, chosenEnum);
        callEnumGetter(getter);
    }

    private Type lookForEnum(Field field) {
        Type chosenEnum = null;
        String fieldName = field.getName();

        for (Field f : chosenClassObject.getClass().getDeclaredFields()) {
            if (f.getName().equals(fieldName))
                chosenEnum = f.getType();
        }


        return chosenEnum;
    }

    private static void callEnumSetter(Method setter, String newValue, Type chosenEnum) {
        try {
            setter.invoke(chosenClassObject, Enum.valueOf((Class<Enum>) chosenEnum, newValue));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void callEnumGetter(Method getter) {
        try {
            setAnswer("New field value: " + getter.invoke(chosenClassObject));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void callString(Field field, String newValue) {
        Method setter = lookForSetter(field);
        Method getter = lookForGetter(field);

        callStringSetter(setter, newValue);
        callStringGetter(getter);

    }

    private void callStringSetter(Method setter, String newValue) {
        try {
            setter.invoke(chosenClassObject, newValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void callStringGetter(Method getter) {
        try {
            setAnswer("New field value: " + getter.invoke(chosenClassObject));

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void callInt(Field field, String newValue) {
        Method setter = lookForSetter(field);
        Method getter = lookForGetter(field);

        callIntSetter(setter, newValue);
        callIntGetter(getter);

    }

    private void callIntSetter(Method setter, String newValue) {
        try {
            setter.invoke(chosenClassObject, Integer.parseInt(newValue));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void callIntGetter(Method getter) {
        try {
            setAnswer("New field value: " + getter.invoke(chosenClassObject));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        FieldOperations.answer = answer;
    }

    public static Object getChosenClassObject() {
        return chosenClassObject;
    }

    public static void setChosenClassObject(Object chosenClassObject) {
        FieldOperations.chosenClassObject = chosenClassObject;
    }
}
