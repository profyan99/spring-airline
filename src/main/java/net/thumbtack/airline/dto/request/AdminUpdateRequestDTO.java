package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.validator.annotation.PasswordValid;

public class AdminUpdateRequestDTO extends UserDTO {

    private String position;

    private String oldPassword;

    @PasswordValid(message = ConstantsSetting.BAD_PASSWORD)
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
