package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.OrderDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderResponseDto extends OrderDto {
    private int orderId;
    private int totalPrice;
    private String flightName;
    private String planeName;
    private String fromTown;
    private String toTown;
    private LocalTime start;
    private LocalTime duration;
    private List<PassengerResponseDto> passengers;

    public OrderResponseDto(int flightId, LocalDate date, int orderId, int totalPrice, String flightName,
                            String planeName, String fromTown, String toTown, LocalTime start, LocalTime duration, List<PassengerResponseDto> passengers) {
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

    public OrderResponseDto() {

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

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public List<PassengerResponseDto> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerResponseDto> passengers) {
        this.passengers = passengers;
    }
}
