package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.BaseUser;

public interface UserMapper {
    int register(BaseUser user);

    boolean exists(String login);
}
