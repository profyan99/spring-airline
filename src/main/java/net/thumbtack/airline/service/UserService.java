package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.dto.request.LoginRequestDto;
import net.thumbtack.airline.dto.response.BaseLoginDto;
import net.thumbtack.airline.model.Country;
import net.thumbtack.airline.model.UserRole;

import java.util.List;

public interface UserService {
    BaseLoginDto login(LoginRequestDto loginRequestDto);

    UserDto get(int id);

    List<Country> getCountries();

    UserCookieDto getUserCookie(String uuid);

    UserCookieDto getUserCookie(String uuid, UserRole role);

    String setUserCookie(UserCookieDto userCookieDto);

    void deleteUserCookie(String uuid);

    boolean exists(String uuid);

}
