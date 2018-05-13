package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.CookieDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.model.UserCookie;
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
public class CookieDaoImpl extends BaseDaoImpl implements CookieDao {

    private static final Logger logger = LoggerFactory.getLogger(CookieDaoImpl.class);

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean exists(String uuid) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getCookieMapper(session).exists(uuid);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist cookie: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Exist cookie");
        }
    }

    @Override
    public Optional<UserCookie> get(String uuid) {
        UserCookie cookie;
        try (SqlSession session = sessionFactory.openSession()) {
            cookie = getCookieMapper(session).get(uuid);
        } catch (RuntimeException e) {
            logger.error("Couldn't getUser cookie: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Get cookie");
        }
        return Optional.ofNullable(cookie);
    }

    @Override
    public void set(UserCookie cookie) {
        try (SqlSession session = sessionFactory.openSession()) {
            getCookieMapper(session).set(cookie);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't set cookie: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Set cookie");
        }
    }

    @Override
    public void delete(String uuid) {
        try(SqlSession session = sessionFactory.openSession()) {
            getCookieMapper(session).delete(uuid);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't delete cookie: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Delete cookie");
        }
    }
}
