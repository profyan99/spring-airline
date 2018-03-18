package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.UserCookie;

public interface CookieDAO {
    UserCookie get(String uuid);

    void set(UserCookie cookie);

    void delete(String uuid);
}