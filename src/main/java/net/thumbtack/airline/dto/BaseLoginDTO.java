// REVU rename to net.thumbtack.airline.dto.response
package net.thumbtack.airline.dto;

// REVU rename to BaseLoginDto
public class BaseLoginDTO extends UserDTO {
    private int id;
    private String userType;

    public BaseLoginDTO(String firstName, String lastName, String patronymic, int id, String userType) {
        super(firstName, lastName, patronymic);
        this.id = id;
        this.userType = userType;
    }

    public BaseLoginDTO() {

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
