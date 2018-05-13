package net.thumbtack.airline.model;

import net.thumbtack.airline.Utils;

import javax.validation.constraints.NotNull;

public class Passenger {
    private int ticket;

    @NotNull(message = Utils.BAD_FIRSTNAME)
    private String firstName;

    @NotNull(message = Utils.BAD_LASTNAME)
    private String lastName;

    private String nationality;

    @NotNull(message = Utils.BAD_PASSPORT)
    private String passport;

    private OrderClass orderClass;

    private int price;

    private String place;

    private int row;

    public Passenger(int ticket, String firstName, String lastName, String nationality, String passport,
                     OrderClass orderClass, int price, String place, int row) {
        this.ticket = ticket;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.passport = passport;
        this.orderClass = orderClass;
        this.price = price;
        this.place = place;
        this.row = row;
    }

    public Passenger(String firstName, String lastName, String nationality, String passport, OrderClass orderClass, int price) {
        this(0, firstName, lastName, nationality, passport, orderClass, price, "", 0);
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
