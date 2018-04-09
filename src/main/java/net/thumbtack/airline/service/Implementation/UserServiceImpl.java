package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.dao.CookieDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.dto.request.LoginRequestDto;
import net.thumbtack.airline.dto.response.AdminResponseDto;
import net.thumbtack.airline.dto.response.BaseLoginDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.*;
import net.thumbtack.airline.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private AdminDao adminDao;
    private ClientDao clientDao;
    private CookieDao cookieDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Autowired
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setCookieDao(CookieDao cookieDao) {
        this.cookieDao = cookieDao;
    }

    @Override
    public BaseLoginDto login(LoginRequestDto loginRequestDto) {
        BaseLoginDto baseLoginDTO;
        BaseUser user = userDao.login(loginRequestDto.getLogin());
        checkUser(user);
        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD.getErrorCodeString(),
                    this.getClass().getSimpleName(), ErrorCode.INVALID_PASSWORD);
        }
        if (user.getUserType().equals(UserRole.ADMIN)) {
            baseLoginDTO = new AdminResponseDto(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getId(),
                    user.getUserType(),
                    adminDao.getAdmin(user.getId()).getPosition()
            );
        } else {
            Client client = clientDao.getClient(user.getId());
            baseLoginDTO = new ClientResponseDto(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getId(),
                    user.getUserType(),
                    client.getPhone(),
                    client.getEmail()
            );
        }
        return baseLoginDTO;
    }

    @Override
    public UserDto get(int id) {
        UserDto userDto;
        BaseUser user = userDao.get(id);
        checkUser(user);
        if (user.getUserType().equals(UserRole.ADMIN)) {
            userDto = new AdminResponseDto(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getId(),
                    user.getUserType(),
                    adminDao.getAdmin(user.getId()).getPosition()
            );
        } else {
            Client client = clientDao.getClient(user.getId());
            userDto = new ClientResponseDto(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getId(),
                    user.getUserType(),
                    client.getPhone(),
                    client.getEmail()
            );
        }
        return userDto;
    }

    @Override
    public List<Country> getCountries() {
        return userDao.getCountries();
    }

    @Override
    public boolean exists(String uuid) {
        return !uuid.isEmpty() && cookieDao.exists(uuid);
    }

    @Override
    public UserCookieDto authorizeUser(String uuid) {
        if(uuid.isEmpty()) {
            throw new BaseException(ErrorCode.UNAUTHORISED_ERROR.getErrorCodeString(), this.getClass().getSimpleName(),
                    ErrorCode.UNAUTHORISED_ERROR);
        }
        UserCookie cookie = cookieDao.get(uuid);
        if(cookie == null) {
            throw new BaseException(ErrorCode.UNAUTHORISED_ERROR.getErrorCodeString(), this.getClass().getSimpleName(),
                    ErrorCode.UNAUTHORISED_ERROR);
        }
        return new UserCookieDto(cookie.getId(), cookie.getUserType());
    }

    @Override
    public UserCookieDto authorizeUser(String uuid, UserRole role) {
        UserCookieDto cookieDTO = authorizeUser(uuid);
        if(!cookieDTO.getUserType().equals(role)) {
            throw new BaseException(ErrorCode.NO_ACCESS.getErrorCodeString(), this.getClass().getSimpleName(),
                    ErrorCode.NO_ACCESS);
        }
        return cookieDTO;
    }

    @Override
    public String setUserCookie(UserCookieDto userCookieDto) {
        UUID uuid = UUID.randomUUID();
        cookieDao.set(new UserCookie(userCookieDto.getId(), userCookieDto.getUserType(), uuid.toString()));
        return uuid.toString();
    }

    @Override
    public void deleteUserCookie(String uuid) {
        cookieDao.delete(uuid);
    }

    private void checkUser(BaseUser user) {
        if (user == null) {
            throw new BaseException(ErrorCode.ACCOUNT_NOT_FOUND.getErrorCodeString(),
                    this.getClass().getSimpleName(), ErrorCode.ACCOUNT_NOT_FOUND);
        }
    }
}
