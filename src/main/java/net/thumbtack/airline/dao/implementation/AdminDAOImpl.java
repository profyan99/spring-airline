package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.AdminDAO;
import net.thumbtack.airline.dao.mapper.AdminMapper;
import net.thumbtack.airline.dao.mapper.ClientMapper;
import net.thumbtack.airline.dao.mapper.UserMapper;
import net.thumbtack.airline.exception.SimpleException;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.Client;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDAOImpl implements AdminDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Admin register(Admin admin) {
        SqlSession session = sessionFactory.openSession();
        try {
            session
                    .getMapper(UserMapper.class)
                    .register(admin);
            session
                    .getMapper(AdminMapper.class)
                    .register(admin);
            session.commit();
            return admin;
        } catch (RuntimeException e) {
            logger.error("Couldn't create admin: " + e.toString());
            session.rollback();
            throw new SimpleException(ConstantsSetting.ErrorsConstants.REGISTRATION_ERROR.toString(), this.getClass().getName(), "");
        } finally {
            session.close();
        }
    }

    @Override
    public Admin getAdmin(int id) {
        Admin admin;
        try (SqlSession session = sessionFactory.openSession()) {
            admin = session
                    .getMapper(AdminMapper.class)
                    .getAdmin(id)
            ;
        } catch (RuntimeException e) {
            logger.error("Couldn't getAdmin admin: " + e.toString());
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString()+" get admin", this.getClass().getName(), "");
        }
        return admin;
    }

    @Override
    public Admin findAdminById(int id) {
        Admin admin;
        try (SqlSession session = sessionFactory.openSession()) {
            admin = session
                            .getMapper(AdminMapper.class)
                            .findAdminById(id)
            ;
        } catch (RuntimeException e) {
            logger.error("Couldn't find by id admin: " + e.toString());
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString()+" find admin by id", this.getClass().getName(), "");
        }
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        SqlSession session = sessionFactory.openSession();
        try {
            session
                    .getMapper(UserMapper.class)
                    .update(admin);
            session
                    .getMapper(AdminMapper.class)
                    .updateAdmin(admin);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't update admin: " + e.toString());
            session.rollback();
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString() + "updating admin",
                    this.getClass().getName(), "");
        } finally {
            session.close();
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients;
        try (SqlSession session = sessionFactory.openSession()) {
           clients = session
                    .getMapper(ClientMapper.class)
                    .getAll();
        } catch (RuntimeException e) {
            logger.error("Couldn't find all clients: " + e.toString());
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString()+" get clients", this.getClass().getName(), "");
        }
        return clients;
    }
}
