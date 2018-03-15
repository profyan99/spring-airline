package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.BaseUser;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    boolean exists(String login);

    BaseUser login(String login);
}
