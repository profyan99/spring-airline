package net.thumbtack.airline.dto;

import java.time.LocalDate;

public class OrderDto {
    private int flightId;

    private LocalDate date;

    public OrderDto(int flightId, LocalDate date) {
        this.flightId = flightId;
        this.date = date;
    }

    public OrderDto() {

    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
