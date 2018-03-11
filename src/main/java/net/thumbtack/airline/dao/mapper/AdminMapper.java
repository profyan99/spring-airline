package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Admin;

public interface AdminMapper {
    Admin register(Admin admin);

    Admin findAdminById(int id);

    Admin update(Admin admin);
}
