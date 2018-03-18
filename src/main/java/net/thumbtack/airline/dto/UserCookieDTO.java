package net.thumbtack.airline.dto;

public class UserCookieDTO {
    private int id;
    private String userType;

    public UserCookieDTO(int id, String userType) {
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
