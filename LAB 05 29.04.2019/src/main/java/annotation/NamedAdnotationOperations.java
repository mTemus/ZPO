package annotation;


import javafx.scene.control.TableColumn;

@Named(name = this.newName)
public class NamedAdnotationOperations {
    //Przykład: @Named(“nazwa”) String getName() powoduje że w nagłówku kolumny widnieje słowo “nazwa” a nie “name”.

    String newName;

    public TableColumn getName(String newName){
        TableColumn column = null;


        return column;
    }



}
