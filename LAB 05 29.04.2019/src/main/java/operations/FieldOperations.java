package operations;

import java.lang.reflect.Field;

public class FieldOperations {

    public StringBuilder getAllFields(Field[] fields) {
        StringBuilder allFields = new StringBuilder();

        for (Field f : fields) {
            allFields.append(f.getName()).append(" : ").append(f.getType()).append("\n");
        }
        return allFields;
    }


}
