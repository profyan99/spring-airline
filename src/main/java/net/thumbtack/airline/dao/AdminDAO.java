package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDAO {

    /**
     * Register User with user type "ADMIN"
     * @param admin {@link Admin}
     * @return {@link Admin} if success with filled field {@link Admin#id}
     */
    Admin register(Admin admin);

    /**
     * Get full {@link Admin} object with filled {@link net.thumbtack.airline.model.BaseUser}
     * @param id user id
     * @return {@link Admin}
     */
    Admin findAdminById(int id);

    /**
     * Get only {@link Admin} object without {@link net.thumbtack.airline.model.BaseUser}
     * @param id user id
     * @return {@link Admin} filled only Admin's fields
     */
    Admin getAdmin(int id);

    /**
     * Update {@link Admin}
     * @param admin {@link Admin}
     */
    void updateAdmin(Admin admin);

    /**
     * Get all users with user type "Client"
     * @return {@link List<Client>}
     *
     * @see Client
     */
    List<Client> getClients();
}
