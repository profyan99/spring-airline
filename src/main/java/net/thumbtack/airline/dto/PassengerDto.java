package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.thumbtack.airline.Utils;
import net.thumbtack.airline.model.OrderClass;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PassengerDto {
    @NotNull(message = Utils.BAD_FIRSTNAME)
    @NotEmpty(message = Utils.BAD_FIRSTNAME)
    private String firstName;

    @NotNull(message = Utils.BAD_LASTNAME)
    @NotEmpty(message = Utils.BAD_FIRSTNAME)
    private String lastName;

    private String nationality;

    @NotNull(message = Utils.BAD_PASSPORT)
    @NotEmpty(message = Utils.BAD_FIRSTNAME)
    private String passport;

    private OrderClass orderClass;

    public PassengerDto(String firstName, String lastName, String nationality, String passport, OrderClass orderClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.passport = passport;
        this.orderClass = orderClass;
    }

    public PassengerDto() {

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

    @JsonProperty("class")
    public OrderClass getOrderClass() {
        return orderClass;
    }

    @JsonProperty("class")
    public void setOrderClass(OrderClass orderClass) {
        this.orderClass = orderClass;
    }

}
