package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.FlightDto;
import net.thumbtack.airline.model.Schedule;

import java.util.List;

public class FlightUpdateRequestDto extends FlightDto {
    private boolean approved;
    private int id;

    public FlightUpdateRequestDto() {

    }

    public FlightUpdateRequestDto(String flightName, String planeName, String fromTown, String toTown, String start,
                                  String duration, int priceBusiness, int priceEconomy,
                                  Schedule schedule, boolean approved) {

        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule);
        this.approved = approved;
        id = 0;
    }

    public FlightUpdateRequestDto(String flightName, String planeName, String fromTown, String toTown, String start,
                                  String duration, int priceBusiness, int priceEconomy,
                                  List<String> dates, boolean approved) {

        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
        this.approved = approved;
        id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
