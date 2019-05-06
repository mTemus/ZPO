package operations;

import controller.ReflectionsController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class FieldOperations {
    private static Object chosenClassObject;
    private static String answer;


    public StringBuilder getAllFields(Field[] fields) {
        StringBuilder allFields = new StringBuilder();

        for (Field f : fields) {
            allFields.append(f.getName()).append(" : ").append(f.getType()).append("\n");
        }
        return allFields;
    }

    private void getTheChosen() {
        setChosenClassObject(ReflectionsController.getChosenClassObject());
    }

    public Type lookForEnum(Field field) {
        Type chosenEnum = null;
        String fieldName = field.getName();

        for (Field f : chosenClassObject.getClass().getDeclaredFields()) {
            if (f.getName().equals(fieldName))
                chosenEnum = f.getType();
        }
        return chosenEnum;
    }

    public void callEnumSetter(Method setter, String newValue, Type chosenEnum) {
        try {
            setter.invoke(chosenClassObject, Enum.valueOf((Class<Enum>) chosenEnum, newValue));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String callEnumGetter(Method getter) {
        try {
            setAnswer("New field value: " + getter.invoke(chosenClassObject));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return getAnswer();
    }

    public void callStringSetter(Method setter, String newValue) {
        try {
            setter.invoke(chosenClassObject, newValue);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String callStringGetter(Method getter) {
        try {
            setAnswer("New field value: " + getter.invoke(chosenClassObject));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return getAnswer();
    }

    public void callIntSetter(Method setter, String newValue) {
        try {
            setter.invoke(chosenClassObject, Integer.parseInt(newValue));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String callIntGetter(Method getter) {
        try {
            setAnswer("New field value: " + getter.invoke(chosenClassObject));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return getAnswer();
    }

    public void callLocalDataSetter(Method setter, String newValue) {
        try {
            setter.invoke(chosenClassObject, LocalDate.parse(newValue));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String callLocalDataSGetter(Method getter) {
        try {
            setAnswer("New field value: " + getter.invoke(chosenClassObject));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return getAnswer();
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
