package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.*;

import java.util.List;
import java.util.Optional;

public interface FlightDao {

    /**
     * Add new flight
     *
     * @param flight      {@link Flight}
     * @return {@link Flight}
     */
    Flight add(Flight flight);

    /**
     * Check for {@link Flight} existing
     *
     * @param flightName name of flight
     * @return true, if exists
     */
    boolean exists(String flightName);

    /**
     * Check for {@link Flight} existing
     *
     * @param flightId id of the flight
     * @return true, if exists
     */
    boolean exists(int flightId);

    /**
     * getUser {@link Flight}
     *
     * @param flightId id of the flight
     * @return {@link Flight}
     */
    Optional<Flight> get(int flightId);

    /**
     * Update {@link Flight}
     *
     * @param flight      - flight object, which will be updated
     * @return {@link Flight}
     */
    Flight update(Flight flight);

    /**
     * Delete {@link Flight} with schedule and dates
     *
     * @param flightId id of the flight
     */
    void delete(int flightId);

    /**
     * Approve  and return {@link Flight}
     *
     * @param flightId - id of the flight
     * @return {@link Flight}
     */
    Flight approve(int flightId);

    /**
     * Get {@link Flight} with conditions
     *
     * @param flightName if it isn't null, search with flightName
     * @param PlaneName  if it isn't null, search with planeName
     * @param FromTown   if it isn't null, search with fromTown
     * @param ToTown     if it isn't null, search with toTown
     * @param FromDate   if it isn't null, search with fromDate
     * @param ToDate     if it isn't null, search with toDate
     * @return {@link List<Flight>}
     */
    List<Flight> getAll(String flightName, String PlaneName, String FromTown, String ToTown, String FromDate, String ToDate);

    /**
     * Get {@link FlightDate} for certain {@link Flight} with certain date
     *
     * @param date     date of the order
     * @param flightId id of the flight
     * @return {@link Optional<Place>}
     */
    Optional<FlightDate> getFlightDate(String date, int flightId);

    /**
     * Update place free status to busy and insert place and row in passenger
     *
     * @param flightDateId id of the {@link FlightDate}
     * @param passenger {@link Passenger}
     */
    void setPassengerPlace(int flightDateId, Passenger passenger);

    /**
     * Get {@link Plane} by name
     *
     * @param planeName name of the plane
     * @return {@link Plane}
     */
    Optional<Plane> getPlane(String planeName);

    /**
     * Try to reserve places in FlightDay
     *
     * @param date             Date of the {@link net.thumbtack.airline.model.Order}
     * @param flightId         id of the {@link Flight}
     * @param passengersAmount amount of places which will be reserved
     * @return 1 if reserved successfully
     */
    int reservePlaces(String date, int flightId, int passengersAmount);

}
