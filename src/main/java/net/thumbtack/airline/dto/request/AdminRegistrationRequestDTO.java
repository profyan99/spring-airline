package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.validator.annotation.LoginValid;
import net.thumbtack.airline.dto.validator.annotation.PasswordValid;

public class AdminRegistrationRequestDTO extends UserDTO {

    private String position;

    @LoginValid(message = "Login isn't valid")
    private String login;

    @PasswordValid(message = "Password isn't valid")
    private String password;

    public AdminRegistrationRequestDTO(String firstName, String lastName, String patronymic,
                                       String position, String login, String password) {

        super(firstName, lastName, patronymic);
        this.position = position;
        this.login = login;
        this.password = password;
    }

    public AdminRegistrationRequestDTO() {

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
