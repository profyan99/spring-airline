package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Client;

public interface ClientDAO {

    /**
     * Register User with user type "CLIENT"
     * @param client {@link Client}
     * @return {@link Client} if success with filled field {@link Client#id}
     */
    Client register(Client client);

    /**
     * Get full {@link Client} object with filled {@link net.thumbtack.airline.model.BaseUser}
     * @param id user id
     * @return {@link Client}
     */
    Client findClientById(int id);

    /**
     * Get only {@link Client} object without {@link net.thumbtack.airline.model.BaseUser}
     * @param id user id
     * @return {@link Client} filled only Client's fields
     */
    Client getClient(int id);
}
