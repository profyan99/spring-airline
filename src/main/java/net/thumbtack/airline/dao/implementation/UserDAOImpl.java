package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.UserDAO;
import net.thumbtack.airline.dao.mapper.UserMapper;
import net.thumbtack.airline.model.BaseUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean exists(String login) {
        try(SqlSession session = sessionFactory.openSession()) {
            return session.getMapper(UserMapper.class).exists(login);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist user: "+e.toString());
            return false;
        }
    }

    @Override
    public BaseUser login(String login) {
        BaseUser user = null;
        try(SqlSession session = sessionFactory.openSession()) {
            user =  session.getMapper(UserMapper.class).login(login);
        } catch (RuntimeException e) {
            logger.error("Couldn't find user by login: "+e.toString());

        }
        return user;
    }
}
