package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.UserDAO;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.BaseUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
            throw new BaseException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString() + "exist user",
                    this.getClass().getName(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public BaseUser login(String login) {
        BaseUser user;
        try (SqlSession session = sessionFactory.openSession()) {
            user = getUserMapper(session).login(login);
        } catch (RuntimeException e) {
            logger.error("Couldn't getAdmin user by getAdmin: " + e.toString());
            throw new BaseException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString() + "getAdmin",
                    this.getClass().getName(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return user;
    }

    @Override
    public BaseUser get(int id) {
        BaseUser user;
        try (SqlSession session = sessionFactory.openSession()) {
            user = getUserMapper(session).get(id);
        } catch (RuntimeException e) {
            logger.error("Couldn't get user by id: " + e.toString());
            throw new BaseException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString() + "get user",
                    this.getClass().getName(), ErrorCode.ERROR_WITH_DATABASE);
        }
        return user;
    }
}
