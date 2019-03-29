package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;

import java.sql.*;

public class EmployeeController {

    Connection connMYSQL;

    @FXML
    private MenuItem mi_exit;

    @FXML
    private TableView<Employee> tbl_employee;

    @FXML
    private TableColumn<Employee, Long> col_id;

    @FXML
    private TableColumn<Employee, String> col_name;

    @FXML
    private TableColumn<Employee, String> col_lastname;

    @FXML
    private TableColumn<Employee, String> col_salary;

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    private void addDataToTable() {
        // konfiguracja wartości z modelu do tabeli
        col_id.setCellValueFactory(new PropertyValueFactory<Employee, Long>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
        col_salary.setCellValueFactory(new PropertyValueFactory<Employee, String>("salary"));
        tbl_employee.setItems(employees);
    }

    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    public void initialize() {

        try {
            connMYSQL = DriverManager.getConnection("jdbc:mysql://localhost:3306/zpo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "admin");
            Statement myStatement = connMYSQL.createStatement();
            ResultSet myResultSet = myStatement.executeQuery("select * from employee");

            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("id") + ", " + myResultSet.getString("name"));

                Employee e = new Employee((long) myResultSet.getInt("id"), myResultSet.getString("name"), myResultSet.getString("email"), myResultSet.getString("salary"));
                employees.add(e);
                addDataToTable();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


//
//            Employee e1 = new Employee((long) 1, "A", "A", "A");
//            Employee e2 = new Employee((long) 2, "B", "B", "B");
//            Employee e3 = new Employee((long) 3, "C", "C", "C");
//            employees.add(e1);
//            employees.add(e2);
//            employees.add(e3);
//            // wypełnienie wartości w tabelce
//            addDataToTable();


        try {
            connMYSQL.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
