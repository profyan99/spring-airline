package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Admin;

public interface AdminMapper {
    int register(Admin admin);

    Admin findAdminById(int id);

    Admin getAdmin(int id);

    void updateAdmin(Admin admin);

    void clearDataBase();
}
