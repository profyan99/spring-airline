package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.FlightDto;
import net.thumbtack.airline.model.Schedule;

import java.util.List;

public class FlightAddRequestDto extends FlightDto {
    private String planeName;

    public FlightAddRequestDto(String flightName, String fromTown, String toTown, String start, String duration,
                               int priceBusiness, int priceEconomy, Schedule schedule, String planeName) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule);
        this.planeName = planeName;
    }

    public FlightAddRequestDto(String flightName, String fromTown, String toTown, String start, String duration,
                               int priceBusiness, int priceEconomy, Schedule schedule, List<String> dates, String planeName) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates);
        this.planeName = planeName;
    }

    public FlightAddRequestDto(String flightName, String fromTown, String toTown, String start, String duration,
                               int priceBusiness, int priceEconomy, List<String> dates, String planeName) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
        this.planeName = planeName;
    }

    public FlightAddRequestDto() {
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }
}
