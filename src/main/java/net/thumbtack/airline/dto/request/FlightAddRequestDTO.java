package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.FlightDTO;
import net.thumbtack.airline.dto.Schedule;

import java.util.List;

public class FlightAddRequestDTO extends FlightDTO {
    public FlightAddRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                               String duration, int priceBusiness, int priceEconomy, Schedule schedule) {
        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule);
    }

    public FlightAddRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                               String duration, int priceBusiness, int priceEconomy, Schedule schedule, List<String> dates) {
        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates);
    }

    public FlightAddRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                               String duration, int priceBusiness, int priceEconomy, List<String> dates) {
        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
    }

    public FlightAddRequestDTO() {
    }
}
