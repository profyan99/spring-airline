package com.thumbtack.airline.model;

public class Client extends BaseUser {
    private String email;
    private String mobileNumber;

    public Client(String firstName, String secondName, String patronymic, String login,
                  String password, String email, String mobileNumber) {

        super(firstName, secondName, patronymic, login, password);
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public Client() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
