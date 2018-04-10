package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.OrderDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Order;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        try (SqlSession session = sessionFactory.openSession()) {
            getOrderMapper(session).addOrder(order);
            getOrderMapper(session).addPassenger(order);
            order.setPassengers(getOrderMapper(session).getPassenger(order.getOrderId()));
            session.commit();
            return order;
        } catch (RuntimeException e) {
            logger.error("Couldn't add order: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " adding order",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public List<Order> get(String fromTown, String toTown, String flightName, String planeName, String fromDate, String toDate,
                           int clientId) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getOrderMapper(session).get(fromTown, toTown, flightName, planeName, fromDate, toDate, clientId);
        } catch (RuntimeException e) {
            logger.error("Couldn't get orders: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " getting orders",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }
}
