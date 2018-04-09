package net.thumbtack.airline.dto.response;

import net.thumbtack.airline.dto.PassengerDto;
import net.thumbtack.airline.model.OrderClass;

public class PassengerResponseDto extends PassengerDto {
    private int ticket;
    private int price;

    public PassengerResponseDto(String firstName, String lastName, String nationality, String passport,
                                OrderClass orderClass, int ticket, int price) {
        super(firstName, lastName, nationality, passport, orderClass);
        this.ticket = ticket;
        this.price = price;
    }

    public PassengerResponseDto() {

    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
