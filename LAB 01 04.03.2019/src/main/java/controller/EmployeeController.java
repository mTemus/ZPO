package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;

import java.sql.*;

public class EmployeeController implements EmployeeDAO {

    @FXML
    private MenuItem mi_exit;

    @FXML
    private TableView<Employee> tbl_employee;

    @FXML
    private TableView<Employee> tbl_find_employee;

    @FXML
    private TableColumn<Employee, Long> col_id;

    @FXML
    private TableColumn<Employee, String> col_name;

    @FXML
    private TableColumn<Employee, String> col_email;

    @FXML
    private TableColumn<Employee, String> col_salary;

    @FXML
    private TableColumn<Employee, Long> col_id_find;

    @FXML
    private TableColumn<Employee, String> col_name_find;

    @FXML
    private TableColumn<Employee, String> col_email_find;

    @FXML
    private TableColumn<Employee, String> col_salary_find;


    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private TextField employee_id_field;

    @FXML
    private TextField employee_name_field;

    @FXML
    public void findEmpolyeeById(ActionEvent event) {
        String employee_id = employee_id_field.getText();
        Integer empl_id = Integer.parseInt(employee_id);

        Employee tmpEmpl = findOne(empl_id);
        employeeId.add(tmpEmpl);
        addDataToFindEmployee(employeeId);

    }

    @FXML
    public void findEmpolyeeByName(ActionEvent event) {

        String empl_name = employee_name_field.getText();
        Employee tmpEmpl = findByName(empl_name);

        employeeName.add(tmpEmpl);
        addDataToFindEmployee(employeeName);

    }

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeId = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeName = FXCollections.observableArrayList();

    private void addDataToEmployees(ObservableList<Employee> employeesList) {
        addDataToTable(employeesList, col_id, col_name, col_email, col_salary, tbl_employee);

    }

    private void addDataToFindEmployee(ObservableList<Employee> employeesList) {
        addDataToTable(employeesList, col_id_find, col_name_find, col_email_find, col_salary_find, tbl_find_employee);

    }

    private void addDataToTable(ObservableList<Employee> employeesList, TableColumn<Employee, Long> col_id_find, TableColumn<Employee, String> col_name_find, TableColumn<Employee, String> col_email_find, TableColumn<Employee, String> col_salary_find, TableView<Employee> tbl_find_employee) {
        col_id_find.setCellValueFactory(new PropertyValueFactory<Employee, Long>("id"));
        col_name_find.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        col_email_find.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        col_salary_find.setCellValueFactory(new PropertyValueFactory<Employee, String>("salary"));
        tbl_find_employee.setItems(employeesList);
    }

    private Connection MySQLConnection() {
        Connection MySQLConnectionion = null;

        try {
            MySQLConnectionion = DriverManager.getConnection("jdbc:mysql://localhost:3306/zpo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return MySQLConnectionion;
    }

    public void initialize() {

        try {
            Statement myStatement = MySQLConnection().createStatement();
            ResultSet myResultSet = myStatement.executeQuery("select * from employee");

            while (myResultSet.next()) {
                Employee e = new Employee((long) myResultSet.getInt("id"),
                        myResultSet.getString("name"),
                        myResultSet.getString("email"),
                        myResultSet.getString("salary"));
                employees.add(e);
                addDataToEmployees(employees);

            }
            MySQLConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee doQuery(PreparedStatement prpStm) {
        Employee tmp_empl = null;

        ResultSet emplResultFind;
        try {
            emplResultFind = prpStm.executeQuery();
            if (emplResultFind.next()) {
                tmp_empl = new Employee((long) emplResultFind.getInt("id"),
                        emplResultFind.getString("name"),
                        emplResultFind.getString("email"),
                        emplResultFind.getString("salary"));
            } else {
                System.out.println("Employee result error");
            }
            MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp_empl;
    }

    public Employee findOne(Integer id_emp) {

        Employee find_empl = null;
        employeeId.clear();

        try {
            PreparedStatement prpStm = MySQLConnection().prepareStatement("select * from employee where id = ?");
            prpStm.setLong(1, id_emp);

            find_empl = doQuery(prpStm);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return find_empl;
    }

    public Employee findByName(String name) {
        Employee find_empl = null;
        employeeName.clear();

        try {
            PreparedStatement prpStm = MySQLConnection().prepareStatement("select * from employee where name LIKE ?");
            prpStm.setString(1, name);

            find_empl = doQuery(prpStm);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return find_empl;
    }

    public Employee findAll() {
        return null;
    }

    public void delete(Employee employee) {

    }

    public void save(Employee employee) {

    }
}
