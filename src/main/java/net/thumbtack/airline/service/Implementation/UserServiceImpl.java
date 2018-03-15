package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.AdminDAO;
import net.thumbtack.airline.dao.ClientDAO;
import net.thumbtack.airline.dao.UserDAO;
import net.thumbtack.airline.dto.BaseLoginDTO;
import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.model.BaseUser;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private AdminDAO adminDAO;
    private ClientDAO clientDAO;

    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public BaseLoginDTO login(LoginRequestDTO loginRequestDTO) {
        BaseLoginDTO baseLoginDTO = null;
        BaseUser user = userDAO.login(loginRequestDTO.getLogin().toLowerCase());
        if(user.getPassword().equals(loginRequestDTO.getPassword())) {
            if (user.getUserType().equals(ConstantsSetting.ADMIN_ROLE)) {
                baseLoginDTO = new AdminResponseDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPatronymic(),
                        user.getId(),
                        user.getUserType(),
                        adminDAO.login(user.getId()).getPosition()
                );
            } else {
                Client client = clientDAO.login(user.getId());
                baseLoginDTO = new ClientResponseDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPatronymic(),
                        user.getId(),
                        user.getUserType(),
                        client.getPhone(),
                        client.getEmail()
                );
            }
        }
        return baseLoginDTO;
    }

    @Override
    public boolean logout(int id) {
        return false;
    }

    @Override
    public UserDTO get(int id) {
        return null;
    }
}
