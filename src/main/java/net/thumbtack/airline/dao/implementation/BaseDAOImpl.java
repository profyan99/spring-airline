package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.mapper.AdminMapper;
import net.thumbtack.airline.dao.mapper.ClientMapper;
import net.thumbtack.airline.dao.mapper.CookieMapper;
import net.thumbtack.airline.dao.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

public class BaseDAOImpl {
    protected AdminMapper getAdminMapper(SqlSession session) {
        return session.getMapper(AdminMapper.class);
    }

    protected ClientMapper getClientMapper(SqlSession session) {
        return session.getMapper(ClientMapper.class);
    }

    protected UserMapper getUserMapper(SqlSession session) {
        return session.getMapper(UserMapper.class);
    }

    protected CookieMapper getCookieMapper(SqlSession session) {
        return session.getMapper(CookieMapper.class);
    }
}
