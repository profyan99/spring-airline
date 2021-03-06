package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.model.UserRole;

public class BaseLoginDto extends UserDto {
    private int id;
    private UserRole userType;

    public BaseLoginDto(String firstName, String lastName, String patronymic, int id, UserRole userType) {
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

    public UserRole getUserType() {
        return userType;
    }

    public void setUserType(UserRole userType) {
        this.userType = userType;
    }
}
