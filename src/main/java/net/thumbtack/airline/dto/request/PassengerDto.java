package net.thumbtack.airline.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.thumbtack.airline.model.OrderClass;

public class PassengerDto {
    private String firstName;
    private String lastName;
    private String nationality;
    private String passport;
    private OrderClass orderClass;

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

    @JsonProperty("clazz")
    public void setOrderClass(OrderClass orderClass) {
        this.orderClass = orderClass;
    }
}
