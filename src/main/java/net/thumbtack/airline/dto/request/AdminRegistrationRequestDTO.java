package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.validator.annotation.LoginValid;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AdminRegistrationRequestDTO extends UserDTO {


    private String position;

    @LoginValid(message = "Login isn't valid")
    private String login;

    //TODO @max and @min from properties
    @NotNull(message = "Password must be not null!")
    @NotBlank(message = "Password must be not blank!")
    @Max(value = 20, message = "Maximal length of password must be 6 characters!")
    @Min(value = 6, message = "Minimal length of password must be 6 characters!")
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
