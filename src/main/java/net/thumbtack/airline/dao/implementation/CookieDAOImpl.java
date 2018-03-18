package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.CookieDAO;
import net.thumbtack.airline.dao.mapper.CookieMapper;
import net.thumbtack.airline.exception.SimpleException;
import net.thumbtack.airline.model.UserCookie;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CookieDAOImpl implements CookieDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserCookie get(String uuid) {
        UserCookie cookie;
        try (SqlSession session = sessionFactory.openSession()) {
            cookie = session
                    .getMapper(CookieMapper.class)
                    .get(uuid);

        } catch (RuntimeException e) {
            logger.error("Couldn't get cookie: " + e.toString());
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString() + "get cookie", this.getClass().getName(), "");
        }
        return cookie;
    }

    @Override
    public void set(UserCookie cookie) {
        SqlSession session = sessionFactory.openSession();
        try {
            session
                    .getMapper(CookieMapper.class)
                    .set(cookie);

            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't set cookie: " + e.toString());
            session.rollback();
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString() + "set cookie", this.getClass().getName(), "");
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String uuid) {
        SqlSession session = sessionFactory.openSession();
        try {
            session
                    .getMapper(CookieMapper.class)
                    .delete(uuid);

            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't delete cookie: " + e.toString());
            session.rollback();
            throw new SimpleException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString() + "delete cookie", this.getClass().getName(), "");
        } finally {
            session.close();
        }
    }
}
