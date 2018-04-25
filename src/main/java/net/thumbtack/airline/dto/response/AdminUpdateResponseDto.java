package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.model.UserRole;

public class AdminUpdateResponseDto extends UserDto {

    private String position;

    private UserRole userType;

    public AdminUpdateResponseDto(String firstName, String lastName, String patronymic, String position, UserRole userType) {
        super(firstName, lastName, patronymic);
        this.position = position;
        this.userType = userType;
    }

    public AdminUpdateResponseDto() {

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        if (!(o instanceof AdminUpdateResponseDto)) return false;

        AdminUpdateResponseDto that = (AdminUpdateResponseDto) o;

        if (getPosition() != null ? !getPosition().equals(that.getPosition()) : that.getPosition() != null)
            return false;
        return getUserType() == that.getUserType();
    }

    @Override
    public int hashCode() {
        int result = getPosition() != null ? getPosition().hashCode() : 0;
        result = 31 * result + (getUserType() != null ? getUserType().hashCode() : 0);
        return result;
    }
}
