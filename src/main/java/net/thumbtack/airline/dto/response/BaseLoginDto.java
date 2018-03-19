package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.UserDTO;

public class BaseLoginDto extends UserDTO {
    private int id;
    private String userType;

    public BaseLoginDto(String firstName, String lastName, String patronymic, int id, String userType) {
        super(firstName, lastName, patronymic);
        this.id = id;
        this.userType = userType;
    }

    public BaseLoginDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
