package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.OrderDto;

import java.util.List;

public class OrderAddResponseDto extends OrderDto {
    private int orderId;
    private int totalPrice;
    private String flightName;
    private String planeName;
    private String fromTown;
    private String toTown;
    private String start;
    private String duration;
    private List<PassengerResponseDto> passengers;

    public OrderAddResponseDto(int flightId, String date, int orderId, int totalPrice, String flightName,
                               String planeName, String fromTown, String toTown, String start, String duration, List<PassengerResponseDto> passengers) {
        super(flightId, date);
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.flightName = flightName;
        this.planeName = planeName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.start = start;
        this.duration = duration;
        this.passengers = passengers;
    }

    public OrderAddResponseDto() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<PassengerResponseDto> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerResponseDto> passengers) {
        this.passengers = passengers;
    }
}
