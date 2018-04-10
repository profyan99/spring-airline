package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.Plane;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {

    private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Admin register(Admin admin) {
        try (SqlSession session = sessionFactory.openSession()) {
            getUserMapper(session).register(admin);
            getAdminMapper(session).register(admin);
            session.commit();
            return admin;
        } catch (RuntimeException e) {
            logger.error("Couldn't create admin: " + e.toString());
            throw new BaseException(ErrorCode.REGISTRATION_ERROR.getErrorCodeString(), ErrorCode.REGISTRATION_ERROR.getErrorFieldString(),
                    ErrorCode.REGISTRATION_ERROR);
        }
    }

    @Override
    public Admin getAdmin(int id) {
        Admin admin;
        try (SqlSession session = sessionFactory.openSession()) {
            admin = getAdminMapper(session).getAdmin(id);
        } catch (RuntimeException e) {
            logger.error("Couldn't getAdmin admin: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " get admin",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return admin;
    }

    @Override
    public Admin findAdminById(int id) {
        Admin admin;
        try (SqlSession session = sessionFactory.openSession()) {
            admin = getAdminMapper(session).findAdminById(id);
        } catch (RuntimeException e) {
            logger.error("Couldn't find by id admin: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " find admin by id",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        try (SqlSession session = sessionFactory.openSession()) {
            getUserMapper(session).update(admin);
            getAdminMapper(session).updateAdmin(admin);

            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't update admin: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "updating admin",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients;
        try (SqlSession session = sessionFactory.openSession()) {
            clients = getClientMapper(session).getAll();
        } catch (RuntimeException e) {
            logger.error("Couldn't find all clients: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " get clients",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return clients;
    }

    @Override
    public List<Plane> getPlanes() {
        List<Plane> planes;
        try (SqlSession session = sessionFactory.openSession()) {
            planes = getPlaneMapper(session).getAll();
        } catch (RuntimeException e) {
            logger.error("Couldn't find all planes: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " get planes",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return planes;
    }

    @Override
    public void clearDataBase() {
        try (SqlSession session = sessionFactory.openSession()) {
            getAdminMapper(session).clearDataBase();
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't clear data base: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " clearing DB",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }
}
