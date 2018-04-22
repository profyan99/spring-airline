package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.FlightDate;
import net.thumbtack.airline.model.Place;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlightMapper {
    int addFlight(Flight flight);

    void addDateAndSchedule(Flight flight);

    boolean existsByName(String flightName);

    boolean existsById(int flightId);

    Flight get(int flightId);

    void update(@Param("flight") Flight flight, @Param("flightDates") List<FlightDate> flightDates);

    void delete(int flightId);

    void approve(int flightId);

    List<Flight> getAll(@Param("flightName") String flightName,
                        @Param("planeName") String planeName,
                        @Param("fromTown") String fromTown,
                        @Param("toTown") String toTown,
                        @Param("fromDate") String fromDate,
                        @Param("toDate") String toDate);

    FlightDate getFlightDate(@Param("date") String date, @Param("flightId") int flightId);

    Place getPlace(@Param("date") String date, @Param("flightId") int flightId,
                   @Param("place") String place, @Param("row") int row);

    void updatePlace(@Param("date") String date, @Param("flightId") int flightId,
                     @Param("place") String place, @Param("row") int row);

    void addPlaces(List<FlightDate> flightDates);

    void addFlightDates(@Param("flight") Flight flight, @Param("flightDates") List<FlightDate> flightDates);

    List<FlightDate> getFlightDatesWithoutPlaces(int flightId);

    int reservePlaces(@Param("date") String date, @Param("flightId") int flightId, @Param("amountPassengers") int amountPassengers);
}
