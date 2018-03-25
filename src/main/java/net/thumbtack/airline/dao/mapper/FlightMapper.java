package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Flight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlightMapper {
    int addFlight(Flight flight);

    void addDateAndSchedule(Flight flight);

    boolean existsByName(String flightName);

    boolean existsById(int flightId);

    Flight get(int flightId);

    void update(Flight flight);

    void delete(int flightId);

    void approve(int flightId);

    List<Flight> getAll(@Param("flightName") String flightName,
                        @Param("planeName") String planeName,
                        @Param("fromTown") String fromTown,
                        @Param("toTown") String toTown,
                        @Param("fromDate") String fromDate,
                        @Param("toDate") String toDate);
}
