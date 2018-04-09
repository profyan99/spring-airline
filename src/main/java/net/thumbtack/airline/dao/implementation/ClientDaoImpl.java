package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Client;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl extends BaseDaoImpl implements ClientDao {

    private static final Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client register(Client client) {
        try (SqlSession session = sessionFactory.openSession()) {
            getUserMapper(session).register(client);
            getClientMapper(session).register(client);

            session.commit();
            return client;
        } catch (RuntimeException e) {
            logger.error("Couldn't create client: " + e.toString());
            throw new BaseException(ErrorCode.REGISTRATION_ERROR.getErrorCodeString(),
                    this.getClass().getSimpleName(), ErrorCode.REGISTRATION_ERROR);
        }
    }

    @Override
    public Client getClient(int id) {
        Client client;
        try (SqlSession session = sessionFactory.openSession()) {
            client = getClientMapper(session).getClient(id);
        } catch (RuntimeException e) {
            logger.error("Couldn't get client: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "get client",
                    this.getClass().getSimpleName(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return client;
    }

    @Override
    public void updateClient(Client client) {
        try (SqlSession session = sessionFactory.openSession()) {
            getUserMapper(session).update(client);
            getClientMapper(session).updateClient(client);

            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't update client: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "updating client",
                    this.getClass().getSimpleName(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public Client findClientById(int id) {
        Client client;
        try (SqlSession session = sessionFactory.openSession()) {
            client = getClientMapper(session).findClientById(id);
        } catch (RuntimeException e) {
            logger.error("Couldn't find by id client: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " find client by id",
                    this.getClass().getSimpleName(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return client;
    }
}
