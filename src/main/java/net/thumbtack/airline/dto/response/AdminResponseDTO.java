package net.thumbtack.airline.dto.response;

public class AdminResponseDTO extends BaseLoginDto {

    private String position;

    public AdminResponseDTO(String firstName, String lastName, String patronymic, int id, String userType, String position) {
        super(firstName, lastName, patronymic, id, userType);
        this.position = position;
    }

    public AdminResponseDTO() {

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
