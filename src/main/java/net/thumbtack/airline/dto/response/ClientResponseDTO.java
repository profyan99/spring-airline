package net.thumbtack.airline.dto.response;

public class ClientResponseDTO extends ClientUpdateResponseDTO {
    private int id;

    public ClientResponseDTO(String firstName, String lastName, String patronymic, String userType,
                             String phone, String email, int id) {

        super(firstName, lastName, patronymic, userType, phone, email);
        this.id = id;
    }

    public ClientResponseDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
