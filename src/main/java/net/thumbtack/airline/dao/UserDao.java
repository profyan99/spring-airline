package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.BaseUser;
import net.thumbtack.airline.model.Country;

import java.util.List;

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
    BaseUser login(String login);

    /**
     * Get {@link BaseUser} by {@link BaseUser#id}
     *
     * @param id User id
     * @return {@link BaseUser}
     */
    BaseUser get(int id);

    /**
     * Get countries' names and iso3166 codes for citizenship
     *
     * @return {@link List<Country>}
     */
    List<Country> getCountries();
}
