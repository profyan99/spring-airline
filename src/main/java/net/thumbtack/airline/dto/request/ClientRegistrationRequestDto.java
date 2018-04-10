package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.dto.validator.annotation.LoginValid;
import net.thumbtack.airline.dto.validator.annotation.PasswordValid;
import net.thumbtack.airline.dto.validator.annotation.PhoneValid;

import javax.validation.constraints.Email;

public class ClientRegistrationRequestDto extends UserDto {

    @Email(message = Utils.BAD_EMAIL,
    regexp = Utils.EMAIL_REGEX)
    private String email;

    @PhoneValid(message = Utils.BAD_PHONE)
    private String phone;

    @LoginValid(message = Utils.BAD_LOGIN)
    private String login;

    @PasswordValid(message = Utils.BAD_PASSWORD)
    private String password;

    public ClientRegistrationRequestDto(String firstName, String lastName, String patronymic, String email,
                                        String phone, String login, String password) {

        super(firstName, lastName, patronymic);
        this.email = email;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public ClientRegistrationRequestDto() {

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
