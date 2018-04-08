package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.FlightDto;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.Schedule;

import java.util.List;

public class FlightAddResponseDto extends FlightDto {
    private int flightId;
    private Plane plane;
    private boolean approved;

    public FlightAddResponseDto(String flightName, String fromTown, String toTown, String start, String duration,
                                int priceBusiness, int priceEconomy, Schedule schedule, List<String> dates, int flightId,
                                Plane plane, boolean approved) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates);
        this.flightId = flightId;
        this.plane = plane;
        this.approved = approved;
    }

    public FlightAddResponseDto(String flightName, String fromTown, String toTown, String start, String duration,
                                int priceBusiness, int priceEconomy, List<String> dates, int flightId, Plane plane,
                                boolean approved) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
        this.flightId = flightId;
        this.plane = plane;
        this.approved = approved;
    }

    public FlightAddResponseDto() {

    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
