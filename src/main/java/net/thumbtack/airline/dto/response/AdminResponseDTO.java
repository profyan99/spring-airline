package net.thumbtack.airline.dto.response;

public class AdminResponseDTO {
    private UserResponseDTO userResponseDTO;

    public AdminResponseDTO(int id, String firstName, String lastName, String patronymic,
                            String position, String userType) {

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(id);
        userResponseDTO.setFirstName(firstName);
        userResponseDTO.setLastName(lastName);
        userResponseDTO.setPatronymic(patronymic);
        userResponseDTO.setPosition(position);
        userResponseDTO.setUserType(userType);
    }

    public AdminResponseDTO() {
        userResponseDTO = new UserResponseDTO();
    }

    public int getId() {
        return userResponseDTO.getId();
    }

    public String getFirstName() {
        return userResponseDTO.getFirstName();
    }

    public String getLastName() {
        return userResponseDTO.getLastName();
    }

    public String getPatronymic() {
        return userResponseDTO.getPatronymic();
    }

    public String getPosition() {
        return userResponseDTO.getPosition();
    }

    public String getUserType() {
        return userResponseDTO.getUserType();
    }

    public void setId(int id) {
        userResponseDTO.setId(id);
    }

    public void setFirstName(String firstName) {
        userResponseDTO.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        userResponseDTO.setLastName(lastName);
    }

    public void setPatronymic(String patronymic) {
        userResponseDTO.setPatronymic(patronymic);
    }

    public void setPosition(String position) {
        userResponseDTO.setPosition(position);
    }

    public void setUserType(String userType) {
        userResponseDTO.setUserType(userType);
    }
}
