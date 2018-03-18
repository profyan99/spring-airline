package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.validator.annotation.PasswordValid;
import net.thumbtack.airline.dto.validator.annotation.PhoneValid;
import org.hibernate.validator.constraints.Email;

public class ClientUpdateRequestDTO extends UserDTO {

    @Email(message = ConstantsSetting.BAD_EMAIL,
    regexp = ConstantsSetting.EMAIL_REGEX)
    private String email;

    @PhoneValid(message = ConstantsSetting.BAD_PHONE)
    private String phone;

    private String oldPassword;

    @PasswordValid(message = ConstantsSetting.BAD_PASSWORD)
    private String newPassword;

    public ClientUpdateRequestDTO(String firstName, String lastName, String patronymic, String email,
                                  String phone, String oldPassword, String newPassword) {

        super(firstName, lastName, patronymic);
        this.email = email;
        this.phone = phone;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ClientUpdateRequestDTO() {

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
