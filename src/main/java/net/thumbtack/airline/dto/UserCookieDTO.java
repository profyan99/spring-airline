package net.thumbtack.airline.dto;

import net.thumbtack.airline.model.UserRole;

public class UserCookieDTO {
    private int id;
    private UserRole userType;

    public UserCookieDTO(int id, UserRole userType) {
        this.id = id;
        this.userType = userType;
    }

    public UserCookieDTO() {

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
