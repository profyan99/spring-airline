package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.dto.validator.annotation.PasswordValid;

public class AdminUpdateRequestDto extends UserDto {

    private int id;

    private String position;

    private String oldPassword;

    @PasswordValid(message = Utils.BAD_PASSWORD)
    private String newPassword;

    public AdminUpdateRequestDto(String firstName, String lastName, String patronymic,
                                 String position, String oldPassword, String newPassword) {

        super(firstName, lastName, patronymic);
        this.position = position;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public AdminUpdateRequestDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminUpdateRequestDto)) return false;

        AdminUpdateRequestDto that = (AdminUpdateRequestDto) o;

        return getId() == that.getId() && (getPosition() != null ? getPosition().equals(that.getPosition()) : that.getPosition() == null)
                && (getOldPassword() != null ? getOldPassword().equals(that.getOldPassword()) : that.getOldPassword() == null)
                && (getNewPassword() != null ? getNewPassword().equals(that.getNewPassword()) : that.getNewPassword() == null);
    }

}
