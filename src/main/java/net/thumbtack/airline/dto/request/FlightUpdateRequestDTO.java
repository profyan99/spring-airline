package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.FlightDTO;
import net.thumbtack.airline.model.Schedule;

import java.util.List;

public class FlightUpdateRequestDTO extends FlightDTO {
    private boolean approved;
    private int id;

    public FlightUpdateRequestDTO() {

    }

    public FlightUpdateRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                                  String duration, int priceBusiness, int priceEconomy,
                                  Schedule schedule, boolean approved) {

        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule);
        this.approved = approved;
        id = 0;
    }

    public FlightUpdateRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
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
