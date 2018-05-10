package net.thumbtack.airline.model;

import java.util.List;

public class Order {
    private int orderId;
    private FlightDate flightDate;
    private BaseUser user;
    private int totalPrice;
    private List<Passenger> passengers;

    public Order(int orderId, FlightDate flightDate, BaseUser user, int totalPrice, List<Passenger> passengers) {
        this.orderId = orderId;
        this.user = user;
        this.totalPrice = totalPrice;
        this.flightDate = flightDate;
        this.passengers = passengers;

    }

    public Order(FlightDate flightDate, BaseUser user, int totalPrice, List<Passenger> passengers) {
        this(0, flightDate, user, totalPrice, passengers);
    }

    public Order() {

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

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public FlightDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(FlightDate flightDate) {
        this.flightDate = flightDate;
    }

    public BaseUser getUser() {
        return user;
    }

    public void setUser(BaseUser user) {
        this.user = user;
    }
}
