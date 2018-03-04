package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.FlightDTO;
import net.thumbtack.airline.dto.Schedule;

import java.util.List;

public class FlightUpdateRequestDTO extends FlightDTO {
    private boolean approved;

    public FlightUpdateRequestDTO() {

    }

    public FlightUpdateRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                                  String duration, int priceBusiness, int priceEconomy,
                                  Schedule schedule, boolean approved) {

        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule);
        this.approved = approved;
    }

    public FlightUpdateRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                                  String duration, int priceBusiness, int priceEconomy,
                                  List<String> dates, boolean approved) {

        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
