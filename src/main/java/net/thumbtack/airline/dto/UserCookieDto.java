package net.thumbtack.airline.dto;

import net.thumbtack.airline.model.UserRole;

public class UserCookieDto {
    private int id;
    private UserRole userType;

    public UserCookieDto(int id, UserRole userType) {
        this.id = id;
        this.userType = userType;
    }

    public UserCookieDto() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCookieDto)) return false;

        UserCookieDto that = (UserCookieDto) o;

        return getId() == that.getId() && getUserType() == that.getUserType();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUserType() != null ? getUserType().hashCode() : 0);
        return result;
    }
}
