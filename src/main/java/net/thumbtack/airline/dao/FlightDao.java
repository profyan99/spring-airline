package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightDao {

    /**
     * Add new flight
     *
     * @param flight {@link Flight}
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
     * @param flightName if it isn't null, search with flightName
     * @param PlaneName if it isn't null, search with planeName
     * @param FromTown if it isn't null, search with fromTown
     * @param ToTown if it isn't null, search with toTown
     * @param FromDate if it isn't null, search with fromDate
     * @param ToDate if it isn't null, search with toDate
     * @return {@link List<Flight>}
     */
    List<Flight> getAll(String flightName, String PlaneName, String FromTown, String ToTown, String FromDate, String ToDate);
}
