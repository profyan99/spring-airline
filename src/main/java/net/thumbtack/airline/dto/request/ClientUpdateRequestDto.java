package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.dto.validator.annotation.PasswordValid;
import net.thumbtack.airline.dto.validator.annotation.PhoneValid;

import javax.validation.constraints.Email;

public class ClientUpdateRequestDto extends UserDto {

    private int id;

    @Email(message = Utils.BAD_EMAIL,
            regexp = Utils.EMAIL_REGEX)
    private String email;

    @PhoneValid(message = Utils.BAD_PHONE)
    private String phone;

    private String oldPassword;

    @PasswordValid(message = Utils.BAD_PASSWORD)
    private String newPassword;

    public ClientUpdateRequestDto(String firstName, String lastName, String patronymic, String email,
                                  String phone, String oldPassword, String newPassword) {

        super(firstName, lastName, patronymic);
        this.email = email;
        this.phone = phone;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ClientUpdateRequestDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
