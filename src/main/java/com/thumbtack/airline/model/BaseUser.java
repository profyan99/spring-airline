package com.thumbtack.airline.model;

public class BaseUser {
    private String firstName;
    private String secondName;
    private String patronymic;

    private String login;
    private String password;

    public BaseUser(String firstName, String secondName, String patronymic, String login, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
    }

    public BaseUser() {

    }
}
