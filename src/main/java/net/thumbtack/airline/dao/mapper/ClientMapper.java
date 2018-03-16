package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Client;

import java.util.List;

public interface ClientMapper {
    Client register(Client client);

    Client getClient(int id);

    Client findClientById(int id);

    List<Client> getAll();
}
