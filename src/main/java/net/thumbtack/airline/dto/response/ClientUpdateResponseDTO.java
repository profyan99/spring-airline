package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.UserDTO;

public class ClientUpdateResponseDTO extends UserDTO {
    private String userType;
    private String phone;
    private String email;

    public ClientUpdateResponseDTO(String firstName, String lastName, String patronymic, String userType,
                                   String phone, String email) {

        super(firstName, lastName, patronymic);
        this.userType = userType;
        this.phone = phone;
        this.email = email;
    }

    public ClientUpdateResponseDTO() {

    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
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
