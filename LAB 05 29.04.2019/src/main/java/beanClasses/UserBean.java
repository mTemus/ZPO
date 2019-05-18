package beanClasses;

import java.io.Serializable;
import java.time.LocalDate;

public class UserBean implements Serializable {

    private int objectID;
    private int id;
    private String login = "null";
    private String password = "null";
    private LocalDate joinDate = LocalDate.now();
    private Permissions permissions = Permissions.NO_PERMISSIONS;

    private enum Permissions {
        USER, ADMINISTRATOR, BOSS, NO_PERMISSIONS
    }

    public UserBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public int getObjectID() {
        return objectID;
    }
}
