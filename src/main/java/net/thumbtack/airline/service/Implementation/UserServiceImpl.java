package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.Utils;
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
import net.thumbtack.airline.dto.response.ServerSettingsResponseDto;
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
import java.util.function.Supplier;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private AdminDao adminDao;
    private ClientDao clientDao;
    private CookieDao cookieDao;
    private Utils utils;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final Supplier<? extends RuntimeException> accountNotFoundException = () ->
        new BaseException(
                ErrorCode.ACCOUNT_NOT_FOUND.getErrorCodeString(),
                ErrorCode.ACCOUNT_NOT_FOUND.getErrorFieldString(),
                ErrorCode.ACCOUNT_NOT_FOUND);

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

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    @Override
    public BaseLoginDto login(LoginRequestDto loginRequestDto, String uuid) {
        if (exists(uuid)) {
            throw new BaseException(ErrorCode.ALREADY_LOGIN.getErrorCodeString(),
                    ErrorCode.ALREADY_LOGIN.getErrorFieldString(), ErrorCode.ALREADY_LOGIN);
        }
        BaseLoginDto baseLoginDTO;
        BaseUser user = userDao.login(loginRequestDto.getLogin()).orElseThrow(accountNotFoundException);
        checkUserExists(user);
        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD.getErrorCodeString(),
                    ErrorCode.INVALID_PASSWORD.getErrorFieldString(), ErrorCode.INVALID_PASSWORD);
        }
        if (user.getUserType().equals(UserRole.ADMIN)) {
            baseLoginDTO = new AdminResponseDto(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getId(),
                    user.getUserType(),
                    adminDao.getAdmin(user.getId()).orElseThrow(accountNotFoundException).getPosition()
            );
        } else {
            Client client = clientDao.getClient(user.getId()).orElseThrow(accountNotFoundException);
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
        BaseUser user = userDao.get(id).orElseThrow(accountNotFoundException);
        checkUserExists(user);
        if (user.getUserType().equals(UserRole.ADMIN)) {
            userDto = new AdminResponseDto(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getId(),
                    user.getUserType(),
                    adminDao.getAdmin(user.getId()).orElseThrow(accountNotFoundException).getPosition()
            );
        } else {
            Client client = clientDao.getClient(user.getId()).orElseThrow(accountNotFoundException);
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
        if (uuid.isEmpty()) {
            throw new BaseException(ErrorCode.UNAUTHORISED_ERROR.getErrorCodeString(),
                    ErrorCode.UNAUTHORISED_ERROR.getErrorFieldString(), ErrorCode.UNAUTHORISED_ERROR);
        }
        UserCookie cookie = cookieDao.get(uuid).orElseThrow(
                () -> new BaseException(ErrorCode.UNAUTHORISED_ERROR.getErrorCodeString(),
                    ErrorCode.UNAUTHORISED_ERROR.getErrorFieldString(), ErrorCode.UNAUTHORISED_ERROR)
        );
        return new UserCookieDto(cookie.getId(), cookie.getUserType());
    }

    @Override
    public UserCookieDto authorizeUser(String uuid, UserRole role) {
        UserCookieDto cookieDTO = authorizeUser(uuid);
        if (!cookieDTO.getUserType().equals(role)) {
            throw new BaseException(ErrorCode.NO_ACCESS.getErrorCodeString(), ErrorCode.NO_ACCESS.getErrorFieldString(),
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
        if (!exists(uuid)) {
            throw new BaseException(ErrorCode.UNAUTHORISED_ERROR.getErrorCodeString(),
                    ErrorCode.UNAUTHORISED_ERROR.getErrorFieldString(), ErrorCode.UNAUTHORISED_ERROR);
        }
        cookieDao.delete(uuid);
    }

    private static void checkUserExists(BaseUser user) {
        if (user == null) {
            throw new BaseException(ErrorCode.ACCOUNT_NOT_FOUND.getErrorCodeString(),
                    ErrorCode.ACCOUNT_NOT_FOUND.getErrorFieldString(), ErrorCode.ACCOUNT_NOT_FOUND);
        }
    }

    @Override
    public ServerSettingsResponseDto getSettings() {
        return new ServerSettingsResponseDto(
                utils.getMaxNameLength(),
                utils.getMinPasswordLength()
        );
    }

    @Override
    public ServerSettingsResponseDto getSettings(UserRole role) {
        // Role not used
        return getSettings();
    }
}