package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.FlightDTO;
import net.thumbtack.airline.model.Schedule;

import java.util.List;

public class FlightGetResponseDTO extends FlightDTO {
    private int flightId;

    public FlightGetResponseDTO(String flightName, String planeName, String fromTown, String toTown, String start, String duration,
                                int priceBusiness, int priceEconomy, Schedule schedule, List<String> dates, int flightId) {
        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates);
        this.flightId = flightId;
    }

    public FlightGetResponseDTO(String flightName, String planeName, String fromTown, String toTown, String start, String duration,
                                int priceBusiness, int priceEconomy, List<String> dates, int flightId) {
        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
        this.flightId = flightId;
    }

    public FlightGetResponseDTO() {
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}