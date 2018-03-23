package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.FlightDTO;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.Schedule;

import java.util.List;

public class FlightAddResponseDTO extends FlightDTO {
    private int flightId;
    private Plane plane;
    private boolean approved;

    public FlightAddResponseDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                                String duration, int priceBusiness, int priceEconomy, Schedule schedule, int flightId,
                                Plane plane, boolean approved,  List<String> dates) {

        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates);
        this.flightId = flightId;
        this.plane = plane;
        this.approved = approved;
    }

    public FlightAddResponseDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                                String duration, int priceBusiness, int priceEconomy, List<String> dates, int flightId,
                                Plane plane, boolean approved) {

        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
        this.flightId = flightId;
        this.plane = plane;
        this.approved = approved;
    }

    public FlightAddResponseDTO() {

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
