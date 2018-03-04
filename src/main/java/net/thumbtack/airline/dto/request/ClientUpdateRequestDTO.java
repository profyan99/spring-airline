package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.validator.annotation.PhoneValid;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ClientUpdateRequestDTO extends UserDTO {

    @Email
    private String email;

    @PhoneValid(message = "Phone isn't valid")
    private String phone;

    private String oldPassword;

    //TODO @max and @min from properties
    @NotNull(message = "Password must be not null!")
    @NotBlank(message = "Password must be not blank!")
    @Max(value = 20, message = "Maximal length of password must be 6 characters!")
    @Min(value = 6, message = "Minimal length of password must be 6 characters!")
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
