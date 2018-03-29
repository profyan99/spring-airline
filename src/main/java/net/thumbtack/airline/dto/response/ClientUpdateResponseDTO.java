package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.model.UserRole;

public class ClientUpdateResponseDTO extends UserDTO {
    private UserRole userType;
    private String phone;
    private String email;

    public ClientUpdateResponseDTO(String firstName, String lastName, String patronymic, UserRole userType,
                                   String phone, String email) {

        super(firstName, lastName, patronymic);
        this.userType = userType;
        this.phone = phone;
        this.email = email;
    }

    public ClientUpdateResponseDTO() {

    }

    public UserRole getUserType() {
        return userType;
    }

    public void setUserType(UserRole userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
