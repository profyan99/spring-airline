package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.UserDTO;

public class AdminUpdateResponseDTO extends UserDTO {

    private String position;

    private String userType;

    public AdminUpdateResponseDTO(String firstName, String lastName, String patronymic, String position, String userType) {
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
