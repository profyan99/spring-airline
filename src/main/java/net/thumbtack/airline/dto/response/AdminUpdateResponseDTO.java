package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.model.UserRole;

public class AdminUpdateResponseDTO extends UserDTO {

    private String position;

    private UserRole userType;

    public AdminUpdateResponseDTO(String firstName, String lastName, String patronymic, String position, UserRole userType) {
        super(firstName, lastName, patronymic);
        this.position = position;
        this.userType = userType;
    }

    public AdminUpdateResponseDTO() {

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
