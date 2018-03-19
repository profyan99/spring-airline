package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.ClientDAO;
import net.thumbtack.airline.dao.mapper.ClientMapper;
import net.thumbtack.airline.dao.mapper.UserMapper;
import net.thumbtack.airline.exception.SimpleException;
import net.thumbtack.airline.model.Client;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAOImpl implements ClientDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client register(Client client) {
    	// REVU use try-with-resource
       SqlSession session = sessionFactory.openSession();
        try {
            session
                    .getMapper(UserMapper.class)
                    .register(client);
            session
                    .getMapper(ClientMapper.class)
                    .register(client);
            session.commit();
            return client;
        } catch (RuntimeException e) {
            logger.error("Couldn't create client: "+e.toString());
            session.rollback();
            throw new SimpleException(ConstantsSetting.ErrorsConstants.REGISTRATION_ERROR.toString(), this.getClass().getName(), "");
        } finally {
            session.close();
        }
    }

    @Override
    public Client getClient(int id) {
        Client client;
        try (SqlSession session = sessionFactory.openSession()) {
            client = session
                    .getMapper(ClientMapper.class)
                    .getClient(id)
            ;
        } catch (RuntimeException e) {
            logger.error("Couldn't get client: " + e.toString());
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString()+"get client", this.getClass().getName(), "");
        }
        return client;
    }

    @Override
    public void updateClient(Client client) {
    	// REVU use try-with-resource
        SqlSession session = sessionFactory.openSession();
        try {
            session
                    .getMapper(UserMapper.class)
                    .update(client);
            session
                    .getMapper(ClientMapper.class)
                    .updateClient(client);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't update client: " + e.toString());
            session.rollback();
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString() + "updating client",
                    this.getClass().getName(), "");
        } finally {
            session.close();
        }
    }

    @Override
    public Client findClientById(int id) {
        Client client;
        try (SqlSession session = sessionFactory.openSession()) {
            client = session
                    .getMapper(ClientMapper.class)
                    .findClientById(id)
            ;
        } catch (RuntimeException e) {
            logger.error("Couldn't find by id client: " + e.toString());
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString()+" find client by id", this.getClass().getName(), "");
        }
        return client;
    }
}
