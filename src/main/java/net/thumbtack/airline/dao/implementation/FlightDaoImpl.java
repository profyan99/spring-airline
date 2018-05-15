package net.thumbtack.airline.dao.implementation;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.FlightDate;
import net.thumbtack.airline.model.Passenger;
import net.thumbtack.airline.model.Plane;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static net.thumbtack.airline.exception.ErrorCode.ERROR_WITH_DATABASE;

@Repository
public class FlightDaoImpl extends BaseDaoImpl implements FlightDao {

    private static final Logger logger = LoggerFactory.getLogger(FlightDaoImpl.class);

    private SqlSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Flight add(Flight flight) {
        try (SqlSession session = sessionFactory.openSession()) {
            getFlightMapper(session).addFlight(flight);
            if(flight.getSchedule() != null) {
                getFlightMapper(session).addSchedule(flight);
            }
            getFlightMapper(session).addFlightDates(flight, flight.getFlightDates());
            getFlightMapper(session).addPlaces(flight.getFlightDates());
            flight.setPlane(getPlaneMapper(session).get(flight.getPlaneName()));
            session.commit();
            return flight;
        } catch (RuntimeException e) {
            logger.error("Couldn't add flight: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Add flight");
        }
    }


    @Override
    public Flight update(Flight flight) {
        try (SqlSession session = sessionFactory.openSession()) {
            getFlightMapper(session).update(flight);
            getFlightMapper(session).addFlightDates(flight, flight.getFlightDates());
            getFlightMapper(session).addPlaces(flight.getFlightDates());
            flight.setPlane(getPlaneMapper(session).get(flight.getPlaneName()));
            session.commit();
            return flight;
        } catch (RuntimeException e) {
            logger.error("Couldn't update flight: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Update flight");
        }
    }

    @Override
    public boolean exists(String flightName) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).existsByName(flightName);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist flight: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Exist flight");
        }
    }

    @Override
    public boolean exists(int flightId) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).existsById(flightId);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist flight: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Exist flight");
        }
    }

    @Override
    public Optional<Flight> get(int flightId) {
        try (SqlSession session = sessionFactory.openSession()) {
            return Optional.ofNullable(getFlightMapper(session).get(flightId));
        } catch (RuntimeException e) {
            logger.error("Couldn't getUser flight: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Get flight");
        }
    }

    @Override
    public void delete(int flightId) {
        try (SqlSession session = sessionFactory.openSession()) {
            getFlightMapper(session).delete(flightId);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't delete flight: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Delete flight");
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
            throw new BaseException(ERROR_WITH_DATABASE, "Approve flight");
        }
    }

    @Override
    public List<Flight> getAll(String flightName, String PlaneName, String FromTown, String ToTown, String FromDate, String ToDate) {
        try (SqlSession session = sessionFactory.openSession()) {
            return getFlightMapper(session).getAll(flightName, PlaneName, FromTown, ToTown, FromDate, ToDate);
        } catch (RuntimeException e) {
            logger.error("Couldn't getUser all flights: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Get all flight");
        }
    }

    @Override
    public Optional<FlightDate> getFlightDate(String date, int flightId) {
        try (SqlSession session = sessionFactory.openSession()) {
             return Optional.ofNullable(getFlightMapper(session).getFlightDate(date, flightId));
        } catch (RuntimeException e) {
            logger.error("Couldn't getUser places: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Get flightDate");
        }
    }

    @Override
    public void setPassengerPlace(int flightDateId, Passenger passenger) {
        try (SqlSession session = sessionFactory.openSession()) {
            getFlightMapper(session).updatePlace(flightDateId, passenger.getPlace(), passenger.getRow());
            getOrderMapper(session).updatePassenger(passenger);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't set passenger place: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Set passenger place");
        }
    }

    @Override
    public Optional<Plane> getPlane(String planeName) {
        try (SqlSession session = sessionFactory.openSession()) {
            return Optional.ofNullable(getPlaneMapper(session).get(planeName));
        } catch (RuntimeException e) {
            logger.error("Couldn't getUser plane: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Get plane");
        }
    }

    @Override
    public int reservePlaces(String date, int flightId, int economyAmount, int businessAmount) {
        try (SqlSession session = sessionFactory.openSession()) {
            int returnValue = 0;
            int economySuccess = getFlightMapper(session).reserveEconomyPlaces(date, flightId, economyAmount);
            int businessSuccess = getFlightMapper(session).reserveBusinessPlaces(date, flightId, businessAmount);
            if(economySuccess == 0) {
                returnValue = 1;
            }
            else if(businessSuccess == 0) {
                returnValue = -1;
            }
            session.commit();
            return returnValue;
        } catch (RuntimeException e) {
            logger.error("Couldn't reserve places: " + e.toString());
            throw new BaseException(ERROR_WITH_DATABASE, "Reserve places");
        }
    }
}
