package net.thumbtack.airline.dto.response;

public class ClientResponseDTO {
    private UserResponseDTO userResponseDTO;

    public ClientResponseDTO(int id, String firstName, String lastName, String patronymic, String phone,
                             String email, String userType) {

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(id);
        userResponseDTO.setFirstName(firstName);
        userResponseDTO.setLastName(lastName);
        userResponseDTO.setPatronymic(patronymic);
        userResponseDTO.setUserType(userType);
        userResponseDTO.setPhone(phone);
        userResponseDTO.setEmail(email);
    }

    public ClientResponseDTO() {
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

    public String getEmail() {
        return userResponseDTO.getEmail();
    }

    public String getPhone() {
        return userResponseDTO.getPhone();
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

    public void setEmail(String email) {
        userResponseDTO.setEmail(email);
    }

    public void setPhone(String phone) {
        userResponseDTO.setPhone(phone);
    }

    public void setUserType(String userType) {
        userResponseDTO.setUserType(userType);
    }

}
