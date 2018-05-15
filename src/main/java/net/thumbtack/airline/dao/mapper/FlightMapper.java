package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.FlightDate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlightMapper {
    int addFlight(Flight flight);

    void addSchedule(@Param("flight") Flight flight);

    boolean existsByName(String flightName);

    boolean existsById(int flightId);

    Flight get(int flightId);

    void update(@Param("flight") Flight flight);

    void delete(int flightId);

    void approve(int flightId);

    List<Flight> getAll(@Param("flightName") String flightName,
                        @Param("planeName") String planeName,
                        @Param("fromTown") String fromTown,
                        @Param("toTown") String toTown,
                        @Param("fromDate") String fromDate,
                        @Param("toDate") String toDate);

    FlightDate getFlightDate(@Param("date") String date, @Param("flightId") int flightId);

    FlightDate getFlightDateById(int flightDateId);

    List<FlightDate> getFlightDateByFlight(int flightId);

    void updatePlace(@Param("flightDateId") int flightDateId,
                     @Param("place") String place, @Param("row") int row);

    void addPlaces(@Param("flightDates") List<FlightDate> flightDates);

    void addFlightDates(@Param("flight") Flight flight, @Param("list") List<FlightDate> flightDates);

    int reserveEconomyPlaces(@Param("date") String date, @Param("flightId") int flightId, @Param("amountEconomy") int amountEconomy);

    int reserveBusinessPlaces(@Param("date") String date, @Param("flightId") int flightId, @Param("amountBusiness") int amountBusiness);
}
