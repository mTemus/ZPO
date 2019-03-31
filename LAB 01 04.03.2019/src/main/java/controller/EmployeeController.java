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
    private TextField employee_delete_field;

    @FXML
    private TextField employee_info_field_delete;

    @FXML
    private TextField employee_add_field_id;

    @FXML
    private TextField employee_add_field_name;

    @FXML
    private TextField employee_add_field_email;

    @FXML
    private TextField employee_add_field_salary;

    @FXML
    private RadioButton sortByNull;

    @FXML
    private RadioButton sortById;

    @FXML
    private RadioButton sortByName;

    @FXML
    private RadioButton sortByEmail;

    @FXML
    private RadioButton sortBySalary;


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

    @FXML
    public void resetTable(ActionEvent event) {
        SortType sorted = null;

        if (sortByNull.isSelected()) {
            sorted = SortType.sortNull;
        } else if (sortById.isSelected()) {
            sorted = SortType.sortById;
        } else if (sortByName.isSelected()) {
            sorted = SortType.sortByName;
        } else if (sortByEmail.isSelected()) {
            sorted = SortType.sortByEmail;
        } else if (sortBySalary.isSelected()) {
            sorted = SortType.sortBySalary;
        }

        if (sorted == SortType.sortNull && sorted != null) {
            employees.clear();
            findAll();
        } else {
            employeeSort.clear();
            sortTable(sorted);
        }

    }

    @FXML
    public void deleteEmployee(ActionEvent event) {
        String employee_id = employee_delete_field.getText();
        Integer empl_id = Integer.parseInt(employee_id);
        Employee existingEmployeeToDelete = findExistingEmployeeToDelete(empl_id);

        if (existingEmployeeToDelete != null)
            delete(existingEmployeeToDelete);
        else
            employee_info_field_delete.setText("Nie ma pracownika o podanym ID.");

    }

    @FXML
    public void findExistingEmployee(ActionEvent event) {
        String emplIdString = employee_add_field_id.getText();

        long employeeToAddId = Long.parseLong(emplIdString);
        String employeeToAddName = employee_add_field_name.getText();
        String employeeToAddEmail = employee_add_field_email.getText();
        String employeeToAddSalary = employee_add_field_salary.getText();

        save(findExistingEmployeeToAddOrUpdate(employeeToAddId, employeeToAddName, employeeToAddEmail, employeeToAddSalary), updateEmployee);

    }

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeId = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeName = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeSort = FXCollections.observableArrayList();

    boolean updateEmployee = Boolean.parseBoolean(null);

    public enum SortType {
        sortById, sortByName, sortByEmail, sortBySalary, sortNull
    }

    private void addDataToEmployee(ObservableList<Employee> employeesList) {
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
        Connection MySQLConnection = null;

        try {
            MySQLConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zpo" +
                            "?useUnicode=true" +
                            "&useJDBCCompliantTimezoneShift=true" +
                            "&useLegacyDatetimeCode=false" +
                            "&serverTimezone=UTC",
                    "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return MySQLConnection;
    }

    public void initialize() {
        findAll();

    }

    public Employee doFindingQuery(PreparedStatement prpStm) {
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

            find_empl = doFindingQuery(prpStm);

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

            find_empl = doFindingQuery(prpStm);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return find_empl;
    }

    public void findAll() {
        try {
            Statement myStatement = MySQLConnection().createStatement();
            ResultSet myResultSet = myStatement.executeQuery("select * from employee");

            while (myResultSet.next()) {
                Employee e = new Employee((long) myResultSet.getInt("id"),
                        myResultSet.getString("name"),
                        myResultSet.getString("email"),
                        myResultSet.getString("salary"));
                employees.add(e);
                addDataToEmployee(employees);

            }
            MySQLConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Employee findExistingEmployeeToDelete(Integer employeeID) {

        Employee tmpEmployee;

        PreparedStatement prpStm = null;
        try {
            prpStm = MySQLConnection().prepareStatement("select * from employee where id = ?");
            prpStm.setLong(1, employeeID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tmpEmployee = doFindingQuery(prpStm);

        if (tmpEmployee != null)
            return tmpEmployee;
        else
            return null;

    }

    public Employee findExistingEmployeeToAddOrUpdate(long id, String name, String email, String salary) {

        Employee employeeToFind = new Employee(id, name, email, salary);

        isThereEmployeeToUpdate(employeeToFind);

        return employeeToFind;
    }

    private void isThereEmployeeToUpdate(Employee employeeToFind) {
        for (Employee emp : employees) {

            if (emp.getId() == employeeToFind.getId()) {
                updateEmployee = true;
                break;
            } else
                updateEmployee = false;
        }
    }


    public void delete(Employee employee) {

        try {

            String Query = "DELETE FROM employee WHERE id = " + employee.getId();

            Statement Stm = MySQLConnection().createStatement();
            Stm.executeUpdate(Query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        employees.clear();
        findAll();

    }

    public void save(Employee employee, boolean updateEmployee) {

        try {
            if (updateEmployee) {

                System.out.println(employee.getId());
                System.out.println(employee.getName());
                System.out.println(employee.getEmail());
                System.out.println(employee.getSalary());

                String Query = "UPDATE employee " +
                        "SET " +
                        "name = '" + employee.getName() + "', " +
                        "email = '" + employee.getEmail() + "', " +
                        "salary = " + employee.getSalary() + " " +
                        "WHERE id = " + employee.getId();
                System.out.println(Query);

                Statement Stm = MySQLConnection().createStatement();
                Stm.executeUpdate(Query);
                employees.clear();
                findAll();

            } else {

                System.out.println(employee.getId());
                System.out.println(employee.getName());
                System.out.println(employee.getEmail());
                System.out.println(employee.getSalary());

                String Query = "INSERT INTO employee " +
                        "(id, " +
                        "name, " +
                        "email, " +
                        "salary) " +
                        "VALUES " +
                        "(" + employee.getId() + ", " +
                        "'" + employee.getName() + "', " +
                        "'" + employee.getEmail() + "', " +
                        employee.getSalary() + ");";

                System.out.println(Query);

                Statement Stm = MySQLConnection().createStatement();
                Stm.executeUpdate(Query);

                employees.clear();
                findAll();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void sortTable(SortType sorted) {

        Statement myStatement;
        ResultSet myResultSet;

        try {
            switch (sorted) {
                case sortById:
                    myStatement = MySQLConnection().createStatement();
                    myResultSet = myStatement.executeQuery("select * from employee order by id DESC");
                    employeeSortList(myResultSet);
                    MySQLConnection().close();
                    break;
                case sortByName:
                    myStatement = MySQLConnection().createStatement();
                    myResultSet = myStatement.executeQuery("select * from employee order by name DESC");
                    employeeSortList(myResultSet);
                    MySQLConnection().close();
                    break;
                case sortByEmail:
                    myStatement = MySQLConnection().createStatement();
                    myResultSet = myStatement.executeQuery("select * from employee order by email DESC");
                    employeeSortList(myResultSet);
                    MySQLConnection().close();
                    break;
                case sortBySalary:
                    myStatement = MySQLConnection().createStatement();
                    myResultSet = myStatement.executeQuery("select * from employee order by salary DESC");
                    employeeSortList(myResultSet);
                    MySQLConnection().close();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void employeeSortList(ResultSet myResultSet) throws SQLException {
        while (myResultSet.next()) {
            Employee e = new Employee((long) myResultSet.getInt("id"),
                    myResultSet.getString("name"),
                    myResultSet.getString("email"),
                    myResultSet.getString("salary"));
            employeeSort.add(e);
               addDataToEmployee(employeeSort);
        }
    }
}
