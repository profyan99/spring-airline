package net.thumbtack.airline.model;

import java.time.LocalDate;
import java.util.List;

public class Flight {
    private int id;
    private String flightName;
    private String fromTown;
    private String toTown;
    private String start;
    private String duration;
    private int priceBusiness;
    private int priceEconomy;
    private Schedule schedule;
    private List<LocalDate> dates;
    private boolean approved;
    private Plane plane;

    public Flight(String flightName, String fromTown, String toTown, String start, String duration,
                  int priceBusiness, int priceEconomy, Schedule schedule, List<LocalDate> dates, boolean approved, Plane plane) {
        this(flightName, fromTown, toTown, start, duration, priceBusiness, priceEconomy, schedule, dates, approved, plane, 0);
    }

    public Flight(String flightName, String fromTown, String toTown, String start, String duration,
                  int priceBusiness, int priceEconomy, Schedule schedule, List<LocalDate> dates, boolean approved, Plane plane,
                  int id) {
        this.flightName = flightName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.start = start;
        this.duration = duration;
        this.priceBusiness = priceBusiness;
        this.priceEconomy = priceEconomy;
        this.schedule = schedule;
        this.dates = dates;
        this.approved = approved;
        this.plane = plane;
        this.id = id;
    }

    public Flight() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getPlaneName() {
        return plane.getName();
    }

    public void setPlaneName(String planeName) {
        this.plane.setName(planeName);
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightName='" + flightName + '\'' +
                ", fromTown='" + fromTown + '\'' +
                ", toTown='" + toTown + '\'' +
                ", start='" + start + '\'' +
                ", duration='" + duration + '\'' +
                ", priceBusiness=" + priceBusiness +
                ", priceEconomy=" + priceEconomy +
                ", schedule=" + (schedule == null) +
                ", dates=" + (dates == null) +
                ", approved=" + approved +
                ", plane=" + (plane==null) +
                '}';
    }
}
