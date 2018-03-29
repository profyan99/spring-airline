package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.BaseUser;
import net.thumbtack.airline.model.Country;

import java.util.List;

public interface UserMapper {
    int register(BaseUser user);

    BaseUser login(String login);

    BaseUser update(BaseUser user);

    boolean exists(String login);

    BaseUser get(int id);

    List<Country> getCountries();
}
