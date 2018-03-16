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
import net.thumbtack.airline.exception.SimpleException;
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
        BaseLoginDTO baseLoginDTO;
        BaseUser user = userDAO.login(loginRequestDTO.getLogin());
        if(user == null) {
            throw new SimpleException(ConstantsSetting.ACCOUNT_NOT_FOUND, this.getClass().getName(), "");
        }
        if(user.getPassword().equals(loginRequestDTO.getPassword())) {
            if (user.getUserType().equals(ConstantsSetting.ADMIN_ROLE)) {
                baseLoginDTO = new AdminResponseDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPatronymic(),
                        user.getId(),
                        user.getUserType(),
                        adminDAO.getAdmin(user.getId()).getPosition()
                );
            } else {
                Client client = clientDAO.getClient(user.getId());
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
        else {
            throw new SimpleException(ConstantsSetting.INVALID_PASSWORD, this.getClass().getName(), "");
        }
        return baseLoginDTO;
    }

    @Override
    public boolean logout(int id) {
        //TODO delete row from cookie table, if id correct and exists
        return false;
    }

    @Override
    public UserDTO get(int id) {
        UserDTO userDTO;
        BaseUser user = userDAO.get(id);
        if(user == null) {
            throw new SimpleException(ConstantsSetting.ACCOUNT_NOT_FOUND, this.getClass().getName(), "");
        }
        if (user.getUserType().equals(ConstantsSetting.ADMIN_ROLE)) {
            userDTO = new AdminResponseDTO(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getId(),
                    user.getUserType(),
                    adminDAO.getAdmin(user.getId()).getPosition()
            );
        } else {
            Client client = clientDAO.getClient(user.getId());
            userDTO = new ClientResponseDTO(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getId(),
                    user.getUserType(),
                    client.getPhone(),
                    client.getEmail()
            );
        }
        return userDTO;
    }
}
