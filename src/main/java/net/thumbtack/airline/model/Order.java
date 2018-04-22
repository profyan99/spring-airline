package net.thumbtack.airline.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int orderId;
    private int flightId;
    private int userId;
    private LocalDate date;
    private int totalPrice;
    private String flightName;
    private String planeName;
    private String fromTown;
    private String toTown;
    private String start;
    private String duration;
    private List<Passenger> passengers;

    public Order(int orderId, int flightId, int userId, LocalDate date, int totalPrice, String flightName, String planeName,
                 String fromTown, String toTown, String start, String duration, List<Passenger> passengers) {
        this.orderId = orderId;
        this.flightId = flightId;
        this.userId = userId;
        this.date = date;
        this.totalPrice = totalPrice;
        this.flightName = flightName;
        this.planeName = planeName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.start = start;
        this.duration = duration;
        this.passengers = passengers;
    }

    public Order(int flightId, int userId, LocalDate date, int totalPrice, String flightName, String planeName, String fromTown,
                 String toTown, String start, String duration, List<Passenger> passengers) {
        this(0, flightId, userId, date, totalPrice, flightName, planeName, fromTown, toTown, start, duration, passengers);
    }

    public Order() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
