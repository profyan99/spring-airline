package net.thumbtack.airline.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ClientRegistrationRequestDTO {

    @Max(20)
    @Min(6)
    private String firstName;

    @Max(20)
    @Min(6)
    private String lastName;

    @Max(20)
    @Min(6)
    private String patronymic;

    private String email;

    private String phone;

    @NotNull
    @Max(20)
    @Min(6)
    private String login;

    @NotNull
    @Max(20)
    @Min(6)
    private String password;

    public ClientRegistrationRequestDTO(String firstName, String lastName, String patronymic,
                                        String email, String phone, String login, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public ClientRegistrationRequestDTO() {

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
