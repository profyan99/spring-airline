package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.UserCookie;

public interface CookieMapper {
    UserCookie get(String uuid);

    void set(UserCookie user);

    void delete(String uuid);
}
