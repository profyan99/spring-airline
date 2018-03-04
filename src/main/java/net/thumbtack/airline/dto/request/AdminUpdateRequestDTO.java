package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.UserDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AdminUpdateRequestDTO extends UserDTO {

    private String position;

    private String oldPassword;

    //TODO @max and @min from properties
    @NotNull(message = "Password must be not null!")
    @NotBlank(message = "Password must be not blank!")
    @Max(value = 20, message = "Maximal length of password must be 6 characters!")
    @Min(value = 6, message = "Minimal length of password must be 6 characters!")
    private String newPassword;

    public AdminUpdateRequestDTO(String firstName, String lastName, String patronymic,
                                 String position, String oldPassword, String newPassword) {

        super(firstName, lastName, patronymic);
        this.position = position;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public AdminUpdateRequestDTO() {

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
