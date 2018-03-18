package net.thumbtack.airline.model;

public class UserCookie {
    private int id;
    private String userType;
    private String uuid;

    public UserCookie(int id, String userType, String uuid) {
        this.id = id;
        this.userType = userType;
        this.uuid = uuid;
    }

    public UserCookie() {

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
