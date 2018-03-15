package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Client;

public interface ClientMapper {
    Client register(Client client);

    Client login(int id);

    Client findClientById(int id);
}
