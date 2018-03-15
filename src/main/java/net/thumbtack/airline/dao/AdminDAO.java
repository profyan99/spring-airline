package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO {
    Admin register(Admin admin);

    Admin findAdminById(int id);

    Admin login(int id);
}
