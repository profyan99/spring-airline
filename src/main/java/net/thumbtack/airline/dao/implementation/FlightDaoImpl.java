package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Flight;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class FlightDaoImpl extends BaseDAOImpl implements FlightDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Flight add(Flight flight) {
        try (SqlSession session = sessionFactory.openSession()) {
            getFlightMapper(session).addFlight(flight);
            getFlightMapper(session).addDateAndSchedule(flight);
            flight.setPlane(getPlaneMapper(session).get(flight.getPlaneName()));
            session.commit();
            return flight;
        } catch (RuntimeException e) {
            logger.error("Couldn't add flight: " + e.toString());
            throw new BaseException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR + " adding flight", this.getClass().getName(),
                    ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public boolean exists(String flightName) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).exists(flightName);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist flight: " + e.toString());
            throw new BaseException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR.toString() + "exist flight",
                    this.getClass().getName(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public Flight get(String flightName) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).get(flightName);
        } catch (RuntimeException e) {
            logger.error("Couldn't get flight: " + e.toString());
            throw new BaseException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR + " getting flight", this.getClass().getName(),
                    ErrorCode.ERROR_WITH_DATABASE);
        }
    }
}
