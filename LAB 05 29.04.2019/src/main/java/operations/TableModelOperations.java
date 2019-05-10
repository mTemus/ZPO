package operations;

import beanClasses.ItemBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TableModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TableModelOperations {

    private MethodOperations MO = new MethodOperations();

    public ObservableList<TableModel> convertItemsOnTableModel(ObservableList<ItemBean> items) throws IllegalAccessException, InvocationTargetException {
        ObservableList<TableModel> models = FXCollections.observableArrayList();

        for (ItemBean IB : items) {
            for (Field f : ((Object) IB).getClass().getDeclaredFields()) {
                Method m = MO.lookForMethod(IB, f, "get");
                String value = m.invoke(IB).toString();

//                System.out.println("Object id: " + IB.getObjectID());
//                System.out.println("Field name: " + f.getName());
//                System.out.println("Field type: " + f.getType().toString());
//                System.out.println("Field value: " + value);

                TableModel tb = new TableModel(IB.getObjectID(), f.getName(), f.getType().toString(), value.toString());
                models.add(tb);
            }
        }

        for (TableModel t : models) {
            System.out.println(t.getId());
            System.out.println(t.getFieldName());
            System.out.println(t.getFieldType());
            System.out.println(t.getFieldValue());
        }

        return models;
    }


}
