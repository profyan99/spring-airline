package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.dto.Schedule;

import java.util.List;

public class FlightAddRequestDTO {
    private String flightName;
    private String planeName;
    private String fromTown;
    private String toTown;
    private String start;
    private String duration;
    private int priceBusiness;
    private int priceEconomy;
    private Schedule schedule;
    private List<String> dates;

    public FlightAddRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                               String duration, int priceBusiness, int priceEconomy, Schedule schedule) {

        this.flightName = flightName;
        this.planeName = planeName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.start = start;
        this.duration = duration;
        this.priceBusiness = priceBusiness;
        this.priceEconomy = priceEconomy;
        this.schedule = schedule;
        dates = null;
    }

    // For FlightAddResponseDTO, because we need schedule and dates together
    public FlightAddRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                               String duration, int priceBusiness, int priceEconomy, Schedule schedule, List<String> dates) {
        this.flightName = flightName;
        this.planeName = planeName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.start = start;
        this.duration = duration;
        this.priceBusiness = priceBusiness;
        this.priceEconomy = priceEconomy;
        this.schedule = schedule;
        this.dates = dates;
    }

    public FlightAddRequestDTO(String flightName, String planeName, String fromTown, String toTown, String start,
                               String duration, int priceBusiness, int priceEconomy, List<String> dates) {

        this.flightName = flightName;
        this.planeName = planeName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.start = start;
        this.duration = duration;
        this.priceBusiness = priceBusiness;
        this.priceEconomy = priceEconomy;
        this.dates = dates;
        schedule = null;
    }

    public FlightAddRequestDTO() {

    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
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

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}
