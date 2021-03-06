package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FlightGetResponseAdminDto extends FlightGetResponseDto {
    private Plane plane;
    private boolean approved;

    public FlightGetResponseAdminDto(String flightName, String fromTown, String toTown, LocalTime start,
                                     LocalTime duration, int priceBusiness, int priceEconomy, Schedule schedule, List<LocalDate> dates,
                                     int flightId, Plane plane, boolean approved) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates, flightId);
        this.plane = plane;
        this.approved = approved;
    }

    public FlightGetResponseAdminDto(String flightName, String fromTown, String toTown, LocalTime start,
                                     LocalTime duration, int priceBusiness, int priceEconomy, List<LocalDate> dates, int flightId,
                                     Plane plane, boolean approved) {
        super(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, dates, flightId);
        this.plane = plane;
        this.approved = approved;
    }

    public FlightGetResponseAdminDto() {
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
