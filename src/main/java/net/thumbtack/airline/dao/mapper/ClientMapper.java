package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Client;

import java.util.List;

public interface ClientMapper {
    int register(Client client);

    Client getClient(int id);

    Client findClientById(int id);

    void updateClient(Client client);

    List<Client> getAll();
}
