package net.thumbtack.airline.dto;

import net.thumbtack.airline.dto.validator.annotation.NameValid;
import net.thumbtack.airline.dto.validator.annotation.PatronymicValid;

public class UserDTO {

    @NameValid(message = "Bad firstName")
    private String firstName;

    @NameValid(message = "Bad lastName")
    private String lastName;

    @PatronymicValid(message = "Bad patronymic")
    private String patronymic;

    public UserDTO(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public UserDTO() {

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
}
