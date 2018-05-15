package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.FlightDto;
import net.thumbtack.airline.model.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FlightGetResponseDto extends FlightDto {
    private int flightId;

    public FlightGetResponseDto(String flightName, String fromTown, String toTown, LocalTime start, LocalTime duration,
                                int priceBusiness, int priceEconomy, Schedule schedule, List<LocalDate> dates, int flightId) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates);
        this.flightId = flightId;
    }

    public FlightGetResponseDto(String flightName, String fromTown, String toTown, LocalTime start, LocalTime duration,
                                int priceBusiness, int priceEconomy, List<LocalDate> dates, int flightId) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
        this.flightId = flightId;
    }

    public FlightGetResponseDto() {
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}
