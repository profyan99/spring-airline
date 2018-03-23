package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.UserCookieDTO;

public interface CookieService {

    boolean exists(String uuid);

    UserCookieDTO getUserCookie(String uuid);

    String setUserCookie(UserCookieDTO user);

    void deleteUserCookie(String uuid);
}
