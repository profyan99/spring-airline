package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Flight;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDao {

    /**
     * Add new flight
     * @param flight {@link Flight}
     * @return {@link Flight}
     */
    Flight add(Flight flight);

    /**
     * Check for {@link Flight} existing
     * @param flightName name of flight
     * @return true, if exists
     */
    boolean exists(String flightName);

    /**
     * get {@link Flight}
     * @param flightName name of flight
     * @return {@link Flight}
     */
    Flight get(String flightName);
}
