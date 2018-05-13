package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.model.BaseUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static net.thumbtack.airline.exception.ErrorCode.ERROR_WITH_DATABASE;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean exists(String login) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getUserMapper(session).exists(login);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist user: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Exist user");
        }
    }

    @Override
    public Optional<BaseUser> login(String login) {
        try (SqlSession session = sessionFactory.openSession()) {
            return Optional.ofNullable(getUserMapper(session).login(login));
        } catch (RuntimeException e) {
            logger.error("Couldn't getAdmin user by getAdmin: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Login user");
        }
    }

    @Override
    public Optional<BaseUser> get(int id) {
        try (SqlSession session = sessionFactory.openSession()) {
            return Optional.ofNullable(getUserMapper(session).get(id));
        } catch (RuntimeException e) {
            logger.error("Couldn't getUser user by id: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Get user");
        }
    }
}
