package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.model.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FlightUpdateRequestDto extends FlightAddRequestDto {
    private int id;

    public FlightUpdateRequestDto() {

    }

    public FlightUpdateRequestDto(String flightName, String fromTown, String toTown, LocalTime start, LocalTime duration,
                                  int priceBusiness, int priceEconomy, Schedule schedule, String planeName, int id) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, planeName);
        this.id = id;
    }

    public FlightUpdateRequestDto(String flightName, String fromTown, String toTown, LocalTime start, LocalTime duration,
                                  int priceBusiness, int priceEconomy, Schedule schedule, List<LocalDate> dates, String planeName, int id) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates, planeName);
        this.id = id;
    }

    public FlightUpdateRequestDto(String flightName, String fromTown, String toTown, LocalTime start, LocalTime duration,
                                  int priceBusiness, int priceEconomy, List<LocalDate> dates, String planeName, int id) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates, planeName);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
