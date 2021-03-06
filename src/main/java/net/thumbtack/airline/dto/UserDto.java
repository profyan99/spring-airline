package net.thumbtack.airline.dto;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dto.validator.annotation.NameValid;
import net.thumbtack.airline.dto.validator.annotation.PatronymicValid;

public class UserDto {

    @NameValid(message = Utils.BAD_FIRSTNAME)
    private String firstName;

    @NameValid(message = Utils.BAD_LASTNAME)
    private String lastName;

    @PatronymicValid(message = Utils.BAD_PATRONYMIC)
    private String patronymic;

    public UserDto(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;

    }

    public UserDto() {

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
