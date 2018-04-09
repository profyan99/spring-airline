package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.OrderDto;
import net.thumbtack.airline.dto.PassengerDto;

import java.util.List;

public class OrderAddRequestDto extends OrderDto {
    private List<PassengerDto> passengers;

    public OrderAddRequestDto(int flightId, String date, List<PassengerDto> passengers) {
        super(flightId, date);
        this.passengers = passengers;
    }

    public OrderAddRequestDto() {

    }

    public List<PassengerDto> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerDto> passengers) {
        this.passengers = passengers;
    }
}
