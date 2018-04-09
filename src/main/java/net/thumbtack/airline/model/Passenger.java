package net.thumbtack.airline.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Passenger {
    private int ticket;

    private String firstName;

    private String lastName;

    private String nationality;

    private String passport;

    private OrderClass orderClass;

    private int price;

    private String place;

    public Passenger(int ticket, String firstName, String lastName, String nationality, String passport,
                     OrderClass orderClass, int price, String place) {
        this.ticket = ticket;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.passport = passport;
        this.orderClass = orderClass;
        this.price = price;
        this.place = place;
    }

    public Passenger(String firstName, String lastName, String nationality, String passport, OrderClass orderClass, int price) {
        this(0, firstName, lastName, nationality, passport, orderClass, price, "");
    }

    public Passenger() {

    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @JsonProperty("clazz")
    public OrderClass getOrderClass() {
        return orderClass;
    }

    public void setOrderClass(OrderClass orderClass) {
        this.orderClass = orderClass;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
