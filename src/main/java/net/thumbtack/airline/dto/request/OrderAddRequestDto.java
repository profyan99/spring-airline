package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.model.Passenger;

import java.util.List;

public class OrderAddRequestDto {
    private int flightId;
    private String date;
    private List<Passenger> passengers;

    public OrderAddRequestDto(int flightId, String date, List<Passenger> passengers) {
        this.flightId = flightId;
        this.date = date;
        this.passengers = passengers;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
