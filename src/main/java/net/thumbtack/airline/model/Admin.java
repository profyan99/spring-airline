package net.thumbtack.airline.model;

public class Admin extends BaseUser {
    private String position;

    public Admin(String firstName, String secondName, String patronymic, String login, String password, String position) {
        super(firstName, secondName, patronymic, login, password, UserRole.ADMIN);
        this.position = position;
    }

    public Admin() {

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
