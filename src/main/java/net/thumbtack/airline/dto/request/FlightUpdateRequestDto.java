package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.model.Schedule;

import java.util.List;

public class FlightUpdateRequestDto extends FlightAddRequestDto {
    private int id;

    public FlightUpdateRequestDto() {

    }

    public FlightUpdateRequestDto(String flightName, String fromTown, String toTown, String start, String duration,
                                  int priceBusiness, int priceEconomy, Schedule schedule, String planeName, int id) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, planeName);
        this.id = id;
    }

    public FlightUpdateRequestDto(String flightName, String fromTown, String toTown, String start, String duration, 
                                  int priceBusiness, int priceEconomy, Schedule schedule, List<String> dates, String planeName, int id) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates, planeName);
        this.id = id;
    }

    public FlightUpdateRequestDto(String flightName, String fromTown, String toTown, String start, String duration,
                                  int priceBusiness, int priceEconomy, List<String> dates, String planeName, int id) {
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
