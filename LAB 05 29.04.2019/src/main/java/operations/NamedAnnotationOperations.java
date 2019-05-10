package operations;

import annotation.Named;

import java.lang.reflect.Method;

public class NamedAnnotationOperations {

    @Named(name = "Kaczka")
    public String getName() {
        Method declaredMethod;
        Named named;
        String newColumnHeader = "";
        
        try {
            declaredMethod = NamedAnnotationOperations.class.getDeclaredMethod("getName");
            named = declaredMethod.getAnnotation(Named.class);
            newColumnHeader = named.name();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return newColumnHeader;
    }


}
