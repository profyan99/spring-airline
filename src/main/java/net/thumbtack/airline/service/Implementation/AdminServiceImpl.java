package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.AdminDAO;
import net.thumbtack.airline.dao.UserDAO;
import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (userDAO.exists(request.getLogin())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.ACCOUNT_EXIST_ERROR.toString(),
                    this.getClass().getName(), ErrorCode.ACCOUNT_EXIST_ERROR);
        }
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
        return responseDTO;
    }

    @Override
    public List<ClientResponseDTO> getClients() {
        List<ClientResponseDTO> responseDTOS = new ArrayList<>();
        adminDAO.getClients().forEach((e) ->
                responseDTOS.add(new ClientResponseDTO(
                        e.getFirstName(),
                        e.getLastName(),
                        e.getPatronymic(),
                        e.getId(),
                        e.getUserType(),
                        e.getPhone(),
                        e.getEmail()
                )));
        return responseDTOS;
    }

    @Override
    public AdminUpdateResponseDTO update(AdminUpdateRequestDTO request) {
        AdminUpdateResponseDTO response;
        Admin admin = adminDAO.findAdminById(request.getId());
        if (!admin.getPassword().equals(request.getOldPassword())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.INVALID_PASSWORD.toString(),
                    this.getClass().getName(), ErrorCode.INVALID_PASSWORD);
        }
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setPatronymic(request.getPatronymic());
        admin.setPassword(request.getNewPassword());
        admin.setPosition(request.getPosition());
        adminDAO.updateAdmin(admin);
        response = new AdminUpdateResponseDTO(
                admin.getFirstName(),
                admin.getLastName(),
                admin.getPatronymic(),
                admin.getPosition(),
                admin.getUserType()
        );
        return response;
    }

    @Override
    public List<Plane> getPlanes() {
        return adminDAO.getPlanes();
    }

}
