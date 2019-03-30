package controller;

import model.Employee;

public interface EmployeeDAO {

    /** * Zwraca pracownika o podanym id */
    Employee findOne(Integer id);

    /** * Zwraca wszystkich pracowników */
    void findAll(); // list

    /** * Zwraca pracownika o podanym nazwisku */
    Employee findByName(String name);

    /** * Usuwa pracownika */
    void delete(Employee employee);

    /** * Dodaje, jeśli brak, lub aktualizuje pracownika */
    void save(Employee employee, boolean update);

}
