package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.thumbtack.airline.Utils;

import java.time.LocalDate;

public class OrderDto {
    private int flightId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.DATE_PATTERN)
    private LocalDate date;

    public OrderDto(int flightId, LocalDate date) {
        this.flightId = flightId;
        this.date = date;
    }

    public OrderDto() {

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
}
