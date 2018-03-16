package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.BaseUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    /**
     * Check for exist user in database
     * @param login user getAdmin
     * @return boolean True, if exist
     */
    boolean exists(String login);

    /**
     * Get {@link BaseUser} by {@link BaseUser#login}
     * @param login User getAdmin
     * @return {@link BaseUser}
     */
    BaseUser login(String login);

    /**
     * Get {@link BaseUser} by {@link BaseUser#id}
     * @param id User id
     * @return {@link BaseUser}
     */
    BaseUser get(int id);
}
