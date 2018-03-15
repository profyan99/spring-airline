package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.AdminDAO;
import net.thumbtack.airline.dao.UserDAO;
import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.exception.SimpleException;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AdminDAO adminDAO;

    private UserDAO userDAO;

    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public AdminResponseDTO register(AdminRegistrationRequestDTO request) {
        AdminResponseDTO responseDTO;
        if(!userDAO.exists(request.getLogin())) {
            Admin admin = new Admin(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getPatronymic(),
                    request.getLogin(),
                    request.getPassword(),
                    request.getPosition()
            );
            adminDAO.register(admin);
            responseDTO = new AdminResponseDTO(
                    admin.getFirstName(),
                    admin.getLastName(),
                    admin.getPatronymic(),
                    admin.getId(),
                    admin.getUserType(),
                    admin.getPosition()
            );
        }
        else {
            throw new SimpleException(ConstantsSetting.ACCOUNT_EXIST_ERROR, this.getClass().getName(), "");
        }
        return responseDTO;
    }

    @Override
    public List<ClientResponseDTO> getClients() {
        return null;
    }

    @Override
    public AdminUpdateResponseDTO update(AdminUpdateRequestDTO request) {
        return null;
    }

    @Override
    public List<Plane> getPlanes() {
        return null;
    }

}
