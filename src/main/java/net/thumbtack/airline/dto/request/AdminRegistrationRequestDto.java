package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.dto.validator.annotation.LoginValid;
import net.thumbtack.airline.dto.validator.annotation.PasswordValid;

public class AdminRegistrationRequestDto extends UserDto {

    private String position;

    @LoginValid(message = Utils.BAD_LOGIN)
    private String login;

    @PasswordValid(message = Utils.BAD_PASSWORD)
    private String password;

    public AdminRegistrationRequestDto(String firstName, String lastName, String patronymic,
                                       String position, String login, String password) {

        super(firstName, lastName, patronymic);
        this.position = position;
        this.login = login;
        this.password = password;
    }

    public AdminRegistrationRequestDto() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminRegistrationRequestDto)) return false;

        AdminRegistrationRequestDto that = (AdminRegistrationRequestDto) o;

        return (getPosition() != null ? getPosition().equals(that.getPosition()) : that.getPosition() == null)
                && (getLogin() != null ? getLogin().equals(that.getLogin()) : that.getLogin() == null)
                && (getPassword() != null ? getPassword().equals(that.getPassword()) : that.getPassword() == null);
    }

}
