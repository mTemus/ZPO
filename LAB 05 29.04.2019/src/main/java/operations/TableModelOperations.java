package operations;

import beanClasses.ItemBean;
import beanClasses.PizzaBean;
import beanClasses.UserBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TableModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TableModelOperations {

    private MethodOperations MO = new MethodOperations();
    private ObservableList<TableModel> models = FXCollections.observableArrayList();

    public ObservableList<TableModel> convertItemsOnTableModel(ObservableList<ItemBean> items) throws IllegalAccessException, InvocationTargetException {
        models.clear();

        for (ItemBean IB : items) {
            for (Field f : ((Object) IB).getClass().getDeclaredFields()) {
                Method m = MO.lookForMethod(IB, f, "get");
                String value = m.invoke(IB).toString();
                TableModel tb = new TableModel(IB.getObjectID(), f.getName(), f.getType().toString(), value);
                models.add(tb);
            }
        }
        return models;
    }

    public ObservableList<TableModel> convertUsersOnTableModel(ObservableList<UserBean> items) throws IllegalAccessException, InvocationTargetException {
        models.clear();

        for (UserBean UB : items) {
            for (Field f : ((Object) UB).getClass().getDeclaredFields()) {
                Method m = MO.lookForMethod(UB, f, "get");
                String value = m.invoke(UB).toString();
                TableModel tb = new TableModel(UB.getObjectID(), f.getName(), f.getType().toString(), value);
                models.add(tb);
            }
        }
        return models;
    }

    public ObservableList<TableModel> convertPizzasOnTableModel(ObservableList<PizzaBean> items) throws IllegalAccessException, InvocationTargetException {
        models.clear();

        for (PizzaBean PB : items) {
            for (Field f : ((Object) PB).getClass().getDeclaredFields()) {
                Method m = MO.lookForMethod(PB, f, "get");
                String value = m.invoke(PB).toString();
                TableModel tb = new TableModel(PB.getObjectID(), f.getName(), f.getType().toString(), value);
                models.add(tb);
            }
        }
        return models;
    }

}
