package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Flight;

public interface FlightMapper {
     int addFlight(Flight flight);

    void addDate(Flight flight);

    void addSchedule(Flight flight);

    boolean exists(String flightName);

    Flight get(String flightName);


}
