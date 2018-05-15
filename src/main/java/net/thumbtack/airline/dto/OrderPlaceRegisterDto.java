package net.thumbtack.airline.dto;

import javax.validation.constraints.Positive;

public class OrderPlaceRegisterDto {
    private int userId;

    @Positive(message = "Bad orderId.")
    private int orderId;

    @Positive(message = "Bad ticket.")
    private int ticket;
    private String lastName;
    private String firstName;
    private String place;

    public OrderPlaceRegisterDto(int orderId, int ticket, String lastName, String firstName, String place) {
        this.orderId = orderId;
        this.ticket = ticket;
        this.lastName = lastName;
        this.firstName = firstName;
        this.place = place;
    }

    public OrderPlaceRegisterDto() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
