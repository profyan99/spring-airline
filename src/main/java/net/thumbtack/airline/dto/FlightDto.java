package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dto.validator.annotation.TimeValid;
import net.thumbtack.airline.model.Schedule;

import java.time.LocalDate;
import java.util.List;

public class FlightDto {
    private String flightName;
    private String fromTown;
    private String toTown;

    @TimeValid
    private String start;

    @TimeValid
    private String duration;
    private int priceBusiness;
    private int priceEconomy;
    private Schedule schedule;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.DATE_PATTERN)
    @JsonSerialize(contentUsing = StringSerializer.class, contentAs = LocalDate.class)
    private List<LocalDate> dates;

    public FlightDto(String flightName, String fromTown, String toTown, String start,
                     String duration, int priceBusiness, int priceEconomy, Schedule schedule) {

        this.flightName = flightName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.start = start;
        this.duration = duration;
        this.priceBusiness = priceBusiness;
        this.priceEconomy = priceEconomy;
        this.schedule = schedule;
        dates = null;
    }

    // For FlightAddResponseDto, because we need schedule and dates together
    public FlightDto(String flightName, String fromTown, String toTown, String start,
                     String duration, int priceBusiness, int priceEconomy, Schedule schedule, List<LocalDate> dates) {
        this.flightName = flightName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.start = start;
        this.duration = duration;
        this.priceBusiness = priceBusiness;
        this.priceEconomy = priceEconomy;
        this.schedule = schedule;
        this.dates = dates;
    }

    public FlightDto(String flightName, String fromTown, String toTown, String start,
                     String duration, int priceBusiness, int priceEconomy, List<LocalDate> dates) {

        this.flightName = flightName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.start = start;
        this.duration = duration;
        this.priceBusiness = priceBusiness;
        this.priceEconomy = priceEconomy;
        this.dates = dates;
        schedule = null;
    }

    public FlightDto() {

    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPriceBusiness() {
        return priceBusiness;
    }

    public void setPriceBusiness(int priceBusiness) {
        this.priceBusiness = priceBusiness;
    }

    public int getPriceEconomy() {
        return priceEconomy;
    }

    public void setPriceEconomy(int priceEconomy) {
        this.priceEconomy = priceEconomy;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }
}
