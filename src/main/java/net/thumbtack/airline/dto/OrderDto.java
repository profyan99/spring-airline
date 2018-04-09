package net.thumbtack.airline.dto;

public class OrderDto {
    private int flightId;
    private String date;

    public OrderDto(int flightId, String date) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
