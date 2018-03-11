package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.Schedule;
import net.thumbtack.airline.dto.FlightDTO;
import net.thumbtack.airline.model.Plane;

import java.util.List;

public class FlightUpdateResponseDTO extends FlightDTO {
    private int flightId;
    private Plane plane;

    public FlightUpdateResponseDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                                String duration, int priceBusiness, int priceEconomy, Schedule schedule, int flightId,
                                Plane plane, List<String> dates) {

        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates);
        this.flightId = flightId;
        this.plane = plane;
    }

    public FlightUpdateResponseDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                                String duration, int priceBusiness, int priceEconomy, List<String> dates, int flightId,
                                Plane plane) {

        super(flightName, planeName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates);
        this.flightId = flightId;
        this.plane = plane;
    }

    public FlightUpdateResponseDTO() {

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
}