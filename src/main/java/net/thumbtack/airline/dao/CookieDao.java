package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.UserCookie;

import java.util.Optional;

public interface CookieDao {

    /**
     * Check user {@link UserCookie} for exist
     *
     * @param uuid unique value, matches cookie
     * @return true, if cookie exists
     */
    boolean exists(String uuid);

    /**
     * Get user {@link UserCookie}
     *
     * @param uuid unique value, matches cookie
     * @return {@link UserCookie}
     */
    Optional<UserCookie> get(String uuid);

    /**
     * Add {@link UserCookie} for {@link net.thumbtack.airline.model.BaseUser}
     *
     * @param cookie {@link UserCookie}
     */
    void set(UserCookie cookie);

    /**
     * Delete {@link UserCookie}
     *
     * @param uuid unique value, matches cookie
     */
    void delete(String uuid);

}
