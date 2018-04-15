package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.Place;
import net.thumbtack.airline.model.Plane;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightDaoImpl extends BaseDaoImpl implements FlightDao {

    private static final Logger logger = LoggerFactory.getLogger(FlightDaoImpl.class);

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Flight add(Flight flight, List<Place> places) {
        try (SqlSession session = sessionFactory.openSession()) {
            getFlightMapper(session).addFlight(flight);
            getFlightMapper(session).addDateAndSchedule(flight);
            getFlightMapper(session).addPlaces(flight, places);
            flight.setPlane(getPlaneMapper(session).get(flight.getPlaneName()));
            session.commit();
            return flight;
        } catch (RuntimeException e) {
            logger.error("Couldn't add flight: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " adding flight",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public boolean exists(String flightName) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).existsByName(flightName);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist flight: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "exist flight",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public boolean exists(int flightId) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).existsById(flightId);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist flight: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + "exist flight",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public Flight get(int flightId) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).get(flightId);
        } catch (RuntimeException e) {
            logger.error("Couldn't get flight: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " getting flight",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public Flight update(Flight flight, List<Place> places) {
        try (SqlSession session = sessionFactory.openSession()) {
            getFlightMapper(session).update(flight, places);
            flight.setPlane(getPlaneMapper(session).get(flight.getPlaneName()));
            session.commit();
            return flight;
        } catch (RuntimeException e) {
            logger.error("Couldn't update flight: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " updating flight",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public void delete(int flightId) {
        try (SqlSession session = sessionFactory.openSession()) {
            getFlightMapper(session).delete(flightId);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't delete flight: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " deleting flight",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public Flight approve(int flightId) {
        try (SqlSession session = sessionFactory.openSession()) {
            getFlightMapper(session).approve(flightId);
            session.commit();
            return getFlightMapper(session).get(flightId);
        } catch (RuntimeException e) {
            logger.error("Couldn't approve flight: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " approving flight",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public List<Flight> getAll(String flightName, String PlaneName, String FromTown, String ToTown, String FromDate, String ToDate) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).getAll(flightName, PlaneName, FromTown, ToTown, FromDate, ToDate);
        } catch (RuntimeException e) {
            logger.error("Couldn't get all flights: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " getting all flights",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public List<Place> getPlaces(String date, int flightId) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).getPlaces(date, flightId);
        } catch (RuntimeException e) {
            logger.error("Couldn't get places: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " getting places",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public Place getPlace(String date, int flightId, String place, int row) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).getPlace(date, flightId, place, row);
        } catch (RuntimeException e) {
            logger.error("Couldn't get place: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " getting place",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }

    @Override
    public Plane getPlane(String planeName) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getPlaneMapper(session).get(planeName);
        } catch (RuntimeException e) {
            logger.error("Couldn't get plane: " + e.toString());
            throw new BaseException(ErrorCode.ERROR_WITH_DATABASE.getErrorCodeString() + " getting plane",
                    ErrorCode.ERROR_WITH_DATABASE.getErrorFieldString(), ErrorCode.ERROR_WITH_DATABASE);
        }
    }
}
