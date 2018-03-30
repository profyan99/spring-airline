package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Client;

public interface ClientDao {

    /**
     * Register User with user type "CLIENT"
     *
     * @param client {@link Client}
     * @return {@link Client} if success with filled field {@link Client#id}
     */
    Client register(Client client);

    /**
     * Get full {@link Client} object with filled {@link net.thumbtack.airline.model.BaseUser}
     *
     * @param id user id
     * @return {@link Client}
     */
    Client findClientById(int id);

    /**
     * Update {@link Client}
     *
     * @param client {@link Client}
     */
    void updateClient(Client client);

    /**
     * Get only {@link Client} object without {@link net.thumbtack.airline.model.BaseUser}
     *
     * @param id user id
     * @return {@link Client} filled only Client's fields
     */
    Client getClient(int id);
}
