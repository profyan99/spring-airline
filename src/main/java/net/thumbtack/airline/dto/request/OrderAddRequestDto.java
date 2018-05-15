package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.OrderDto;
import net.thumbtack.airline.dto.PassengerDto;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

public class OrderAddRequestDto extends OrderDto {
    @Valid
    private List<PassengerDto> passengers;
    private int userId;

    public OrderAddRequestDto(int flightId, int userId, LocalDate date, List<PassengerDto> passengers) {
        super(flightId, date);
        this.passengers = passengers;
        this.userId = userId;
    }

    public OrderAddRequestDto() {

    }

    public List<PassengerDto> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerDto> passengers) {
        this.passengers = passengers;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
