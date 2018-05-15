package net.thumbtack.airline.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import net.thumbtack.airline.model.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FlightDto {
    private String flightName;
    private String fromTown;
    private String toTown;
    private LocalTime start;
    private LocalTime duration;
    private int priceBusiness;
    private int priceEconomy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Schedule schedule;

    private List<LocalDate> dates;

    public FlightDto(String flightName, String fromTown, String toTown, LocalTime start,
                     LocalTime duration, int priceBusiness, int priceEconomy, Schedule schedule) {

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
    public FlightDto(String flightName, String fromTown, String toTown, LocalTime start,
                     LocalTime duration, int priceBusiness, int priceEconomy, Schedule schedule, List<LocalDate> dates) {
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

    public FlightDto(String flightName, String fromTown, String toTown, LocalTime start,
                     LocalTime duration, int priceBusiness, int priceEconomy, List<LocalDate> dates) {

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

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
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
