package net.thumbtack.airline.dto.response;

public class AdminResponseDTO extends AdminUpdateResponseDTO {

    private int id;

    public AdminResponseDTO(String firstName, String lastName, String patronymic, String position, String userType, int id) {
        super(firstName, lastName, patronymic, position, userType);
        this.id = id;
    }

    public AdminResponseDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
