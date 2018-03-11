package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.ClientDAO;
import net.thumbtack.airline.dao.mapper.ClientMapper;
import net.thumbtack.airline.dao.mapper.UserMapper;
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
            return client;
        } finally {
            session.close();
        }
    }

    @Override
    public Client findClientById(int id) {
        Client client = null;
        try (SqlSession session = sessionFactory.openSession()) {
            client = session
                    .getMapper(ClientMapper.class)
                    .findClientById(id)
            ;
        } catch (RuntimeException e) {
            logger.error("Couldn't find by id client: " + e.toString());
        }
        return client;
    }
}
