package annotation;

import javafx.scene.control.TableColumn;

import javax.xml.soap.Name;
import java.lang.reflect.Method;
import java.util.Locale;


public class NamedAdnotationOperations {
    //Przykład: @Named(“nazwa”) String getName() powoduje że w nagłówku kolumny widnieje słowo “nazwa” a nie “name”.

    @Named(name = "kaczka")
    public String getName() {
        Method declaredMethod;
        Named named = null;
        String newColumnHeader = "";
        
        try {
            declaredMethod = NamedAdnotationOperations.class.getDeclaredMethod("getName");
            named = declaredMethod.getAnnotation(Named.class);
            newColumnHeader = named.name();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return newColumnHeader;
    }


}
