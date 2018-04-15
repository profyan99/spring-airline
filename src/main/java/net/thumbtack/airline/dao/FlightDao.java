package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.Place;
import net.thumbtack.airline.model.Plane;

import java.util.List;

public interface FlightDao {

    /**
     * Add new flight
     *
     * @param flight {@link Flight}
     * @param places {@link List<Place>} for all dates in flight
     * @return {@link Flight}
     */
    Flight add(Flight flight, List<Place> places);

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
     * get {@link Flight}
     *
     * @param flightId id of the flight
     * @return {@link Flight}
     */
    Flight get(int flightId);

    /**
     * Update {@link Flight}
     *
     * @param flight - flight object, which will be updated
     * @param places {@link List<Place>} for all dates in flight
     * @return {@link Flight}
     */
    Flight update(Flight flight, List<Place> places);

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
     * Get free {@link List<Place>} for certain {@link Flight} with certain date
     * @param date date of the order
     * @param flightId id of the flight
     * @return {@link List<Place>}
     */
    List<Place> getPlaces(String date, int flightId);

    /**
     * Get {@link Place}
     * @param date date of the order
     * @param flightId id of the flight
     * @param place string literal of place (A, B, ...)
     * @param row integer value of row (1, 2, ...)
     * @return {@link Place}
     */
    Place getPlace(String date, int flightId, String place, int row);

    /**
     * Get {@link Plane} by name
     * @param planeName name of the plane
     * @return {@link Plane}
     */
    Plane getPlane(String planeName);
}
