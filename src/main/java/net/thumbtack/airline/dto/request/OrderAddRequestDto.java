package net.thumbtack.airline.dto.request;

import java.util.List;

public class OrderAddRequestDto {
    private int flightId;
    private String date;
    private List<PassengerDto> passengers;

    public OrderAddRequestDto(int flightId, String date, List<PassengerDto> passengers) {
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

    public List<PassengerDto> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerDto> passengers) {
        this.passengers = passengers;
    }
}
