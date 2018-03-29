package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.model.UserRole;

public class ClientResponseDTO extends BaseLoginDto {

    private String phone;
    private String email;

    public ClientResponseDTO(String firstName, String lastName, String patronymic, int id, UserRole userType,
                             String phone, String email) {
        super(firstName, lastName, patronymic, id, userType);
        this.phone = phone;
        this.email = email;
    }

    public ClientResponseDTO() {

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

    @Override
    public String toString() {
        return "ClientResponseDTO{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
