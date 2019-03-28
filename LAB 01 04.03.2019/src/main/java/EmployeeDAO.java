package java;

public interface EmployeeDAO {
    /** * Zwraca pracownika o podanym id */
    Optional findOne(Integer id);
    /** * Zwraca wszystkich pracowników */
    List findAll();
    /** * Zwraca pracownika o podanym nazwisku */
    Optional findByName(String name);
    /** * Usuwa pracownika */
    void delete(Employee employee);
    /** * Dodaje, jeśli brak, lub aktualizuje pracownika */
    void save(Employee employee);
}
