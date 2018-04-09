package net.thumbtack.airline.model;

public class Client extends BaseUser {
    private String email;
    private String phone;

    public Client(String firstName, String secondName, String patronymic, String login,
                  String password, String email, String phone) {

        super(firstName, secondName, patronymic, login, password, UserRole.CLIENT);
        this.email = email;
        this.phone = phone;
    }

    public Client() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
