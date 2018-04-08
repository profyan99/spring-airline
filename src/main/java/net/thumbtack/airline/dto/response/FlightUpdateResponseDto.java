package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.Schedule;

import java.util.List;

public class FlightUpdateResponseDto extends FlightAddResponseDto {

    public FlightUpdateResponseDto(String flightName, String fromTown, String toTown, String start, String duration,
                                   int priceBusiness, int priceEconomy, Schedule schedule, List<String> dates,
                                   int flightId, Plane plane, boolean approved) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates, flightId, plane, approved);
    }

    public FlightUpdateResponseDto(String flightName, String fromTown, String toTown, String start, String duration,
                                   int priceBusiness, int priceEconomy, List<String> dates, int flightId, Plane plane,
                                   boolean approved) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates, flightId, plane, approved);
    }

    public FlightUpdateResponseDto() {
    }
}
