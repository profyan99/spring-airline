package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.mapper.*;
import org.apache.ibatis.session.SqlSession;

public class BaseDaoImpl {
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

    protected PlaneMapper getPlaneMapper(SqlSession session) {
        return session.getMapper(PlaneMapper.class);
    }

    protected FlightMapper getFlightMapper(SqlSession session) { return session.getMapper(FlightMapper.class); }

    protected OrderMapper getOrderMapper(SqlSession session) { return session.getMapper(OrderMapper.class); }
}
