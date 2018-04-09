package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.CookieDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.UserCookie;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CookieDaoImpl extends BaseDaoImpl implements CookieDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean exists(String uuid) {
        try(SqlSession session = sessionFactory.openSession()) {
            return getCookieMapper(session).exists(uuid);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist cookie: "+e.toString());
            // REVU you do not need to pass ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString()
            // if you pass ErrorCode.ERROR_WITH_DATABASE
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "exist cookie",
                    this.getClass().getSimpleName(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public UserCookie get(String uuid) {
        UserCookie cookie;
        try (SqlSession session = sessionFactory.openSession()) {
            cookie = getCookieMapper(session).get(uuid);

        } catch (RuntimeException e) {
            logger.error("Couldn't get cookie: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "get cookie",
                    this.getClass().getSimpleName(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return cookie;
    }

    @Override
    public void set(UserCookie cookie) {
        try(SqlSession session = sessionFactory.openSession()) {
            getCookieMapper(session).set(cookie);

            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't set cookie: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "set cookie",
                    this.getClass().getSimpleName(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public void delete(String uuid) {
        SqlSession session = sessionFactory.openSession();
        try {
            getCookieMapper(session).delete(uuid);

            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't delete cookie: " + e.toString());
            session.rollback();
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "delete cookie",
                    this.getClass().getSimpleName(), ErrorCode.ERROR_WITH_DATABASE);
        } finally {
            session.close();
        }
    }
}
