package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.model.UserRole;

public class AdminUpdateResponseDto extends UserDto {

    private String position;

    private UserRole userType;

    public AdminUpdateResponseDto(String firstName, String lastName, String patronymic, String position, UserRole userType) {
        super(firstName, lastName, patronymic);
        this.position = position;
        this.userType = userType;
    }

    public AdminUpdateResponseDto() {

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public UserRole getUserType() {
        return userType;
    }

    public void setUserType(UserRole userType) {
        this.userType = userType;
    }
}
