package net.thumbtack.airline.model;

public class BaseUser {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    // REVU change to enum UserRole userType
    private String userType;

    private String login;
    private String password;

    public BaseUser(String firstName, String lastName, String patronymic, String login, String password, String userType) {
        this(0, firstName, lastName, patronymic, userType, login, password);
    }

    public BaseUser(int id, String firstName, String lastName, String patronymic, String userType, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.userType = userType;
        this.login = login;
        this.password = password;
    }

    public BaseUser() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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


}
