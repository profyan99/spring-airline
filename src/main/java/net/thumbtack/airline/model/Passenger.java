package net.thumbtack.airline.model;

import net.thumbtack.airline.ConstantsSetting;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Passenger {
    private int ticket;

    @NotNull(message = ConstantsSetting.BAD_FIRSTNAME)
    @NotBlank(message = ConstantsSetting.BAD_FIRSTNAME)
    private String firstName;

    @NotNull(message = ConstantsSetting.BAD_LASTNAME)
    @NotBlank(message = ConstantsSetting.BAD_LASTNAME)
    private String lastName;

    private String nationality;

    @NotNull(message = ConstantsSetting.BAD_PASSPORT)
    @NotBlank(message = ConstantsSetting.BAD_PASSPORT)
    private String passport;

    private OrderClass orderClass;

    private int price;

    public Passenger(int ticket, String firstName, String lastName, String nationality, String passport,
                     OrderClass orderClass, int price) {
        this.ticket = ticket;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.passport = passport;
        this.orderClass = orderClass;
        this.price = price;
    }

    public Passenger(String firstName, String lastName, String nationality, String passport, OrderClass orderClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.passport = passport;
        this.orderClass = orderClass;
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
}
