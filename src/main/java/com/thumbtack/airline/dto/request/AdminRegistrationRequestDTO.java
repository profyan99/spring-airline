package com.thumbtack.airline.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AdminRegistrationRequestDTO {
    //TODO @max and @min from properties

    @Max(20)
    @Min(6)
    private String firstName;

    @Max(20)
    @Min(6)
    private String lastName;

    @Max(20)
    @Min(6)
    private String patronymic;

    private String position;

    @NotNull
    @Max(20)
    @Min(6)
    private String login;

    @NotNull
    @Max(20)
    @Min(6)
    private String password;

    public AdminRegistrationRequestDTO(String firstName, String lastName, String patronymic,
                                       String position, String login, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
        this.login = login;
        this.password = password;
    }

    public AdminRegistrationRequestDTO() {

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
