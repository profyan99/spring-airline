package net.thumbtack.airline.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    boolean exists(String login);
}
