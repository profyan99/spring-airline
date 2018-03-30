package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.model.UserRole;

public class AdminResponseDto extends BaseLoginDto {

    private String position;

    public AdminResponseDto(String firstName, String lastName, String patronymic, int id, UserRole userType, String position) {
        super(firstName, lastName, patronymic, id, userType);
        this.position = position;
    }

    public AdminResponseDto() {

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
