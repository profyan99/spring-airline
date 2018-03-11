package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Client;

public interface ClientDAO {
    Client register(Client client);

    Client findClientById(int id);
}
