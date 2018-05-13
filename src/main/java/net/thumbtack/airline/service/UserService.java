package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.UserDto;
import net.thumbtack.airline.dto.request.LoginRequestDto;
import net.thumbtack.airline.dto.response.BaseLoginDto;
import net.thumbtack.airline.dto.response.ServerSettingsResponseDto;
import net.thumbtack.airline.model.UserRole;

public interface UserService {
    BaseLoginDto login(LoginRequestDto loginRequestDto, String uuid);

    UserDto getUser(int id);

    UserCookieDto authorizeUser(String uuid);

    UserCookieDto authorizeUser(String uuid, UserRole role);

    String setUserCookie(UserCookieDto userCookieDto);

    void deleteUserCookie(String uuid);

    boolean exists(String uuid);

    ServerSettingsResponseDto getSettings();

    ServerSettingsResponseDto getSettings(UserRole role);
}
