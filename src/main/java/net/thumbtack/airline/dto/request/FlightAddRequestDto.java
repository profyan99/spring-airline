package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.FlightDto;
import net.thumbtack.airline.model.Schedule;

import java.util.List;

public class FlightAddRequestDto extends FlightDto {
    public FlightAddRequestDto(String flightName, String planeName, String fromTown, String toTown, String start,
                               String duration, int priceBusiness, int priceEconomy, Schedule schedule) {
        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule);
    }

    public FlightAddRequestDto(String flightName, String planeName, String fromTown, String toTown, String start,
                               String duration, int priceBusiness, int priceEconomy, Schedule schedule, List<String> dates) {
        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates);
    }

    public FlightAddRequestDto(String flightName, String planeName, String fromTown, String toTown, String start,
                               String duration, int priceBusiness, int priceEconomy, List<String> dates) {
        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
    }

    public FlightAddRequestDto() {
    }
}
