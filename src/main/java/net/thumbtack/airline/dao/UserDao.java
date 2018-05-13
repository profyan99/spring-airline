package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.BaseUser;

import java.util.Optional;

public interface UserDao {

    /**
     * Check for exist user in database
     *
     * @param login user getAdmin
     * @return boolean True, if exist
     */
    boolean exists(String login);

    /**
     * Get {@link BaseUser} by {@link BaseUser#login}
     *
     * @param login User getAdmin
     * @return {@link BaseUser}
     */
    Optional<BaseUser> login(String login);

    /**
     * Get {@link BaseUser} by {@link BaseUser#id}
     *
     * @param id User id
     * @return {@link BaseUser}
     */
    Optional<BaseUser> get(int id);

}
