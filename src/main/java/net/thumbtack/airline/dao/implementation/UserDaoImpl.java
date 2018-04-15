package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.BaseUser;
import net.thumbtack.airline.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "exist user",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public Optional<BaseUser> login(String login) {
        BaseUser user;
        try (SqlSession session = sessionFactory.openSession()) {
            user = getUserMapper(session).login(login);
        } catch (RuntimeException e) {
            logger.error("Couldn't getAdmin user by getAdmin: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "getAdmin",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<BaseUser> get(int id) {
        BaseUser user;
        try (SqlSession session = sessionFactory.openSession()) {
            user = getUserMapper(session).get(id);
        } catch (RuntimeException e) {
            logger.error("Couldn't get user by id: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "get user",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<Country> getCountries() {
        try (SqlSession session = sessionFactory.openSession()) {
            return getUserMapper(session).getCountries();
        } catch (RuntimeException e) {
            logger.error("Couldn't get countries for citizenship: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "get countries",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }
}
