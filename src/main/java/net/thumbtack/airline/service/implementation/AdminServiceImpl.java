// REVU no capital letters!
// REVU net.thumbtack.airline.service.implementation;
package net.thumbtack.airline.service.implementation;

import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.request.AdminRegistrationRequestDto;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDto;
import net.thumbtack.airline.dto.response.AdminResponseDto;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static net.thumbtack.airline.exception.ErrorCode.*;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    private AdminDao adminDao;

    private UserDao userDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public AdminResponseDto register(AdminRegistrationRequestDto request) {
        AdminResponseDto responseDTO;
        if (userDao.exists(request.getLogin())) {
            throw new BaseException(ACCOUNT_EXIST_ERROR);
        }
        Admin admin = new Admin(
                request.getFirstName(),
                request.getLastName(),
                request.getPatronymic(),
                request.getLogin(),
                request.getPassword(),
                request.getPosition()
        );
        adminDao.register(admin);
        responseDTO = new AdminResponseDto(
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
    public List<ClientResponseDto> getClients() {
        List<ClientResponseDto> responseDTOS = new ArrayList<>();
        adminDao.getClients().forEach((e) ->
                responseDTOS.add(new ClientResponseDto(
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
    public AdminUpdateResponseDto update(AdminUpdateRequestDto request) {
        AdminUpdateResponseDto response;
        Admin admin = adminDao.findAdminById(request.getId()).orElseThrow(() ->
                new BaseException(ACCOUNT_NOT_FOUND)
        );
        if (!admin.getPassword().equals(request.getOldPassword())) {
            throw new BaseException(INVALID_PASSWORD);
        }
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setPatronymic(request.getPatronymic());
        admin.setPassword(request.getNewPassword());
        admin.setPosition(request.getPosition());
        adminDao.updateAdmin(admin);
        response = new AdminUpdateResponseDto(
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
        return adminDao.getPlanes();
    }

    @Override
    public void clearDataBase() {
        adminDao.clearDataBase();
    }
}
