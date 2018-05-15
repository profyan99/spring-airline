package net.thumbtack.airline.model;

public class Place {
    private int row;
    private String place;
    private OrderClass type;
    private boolean free;
    private FlightDate flightDate;

    public Place(int row, String place, OrderClass type) {
        this(row, place, type, true, new FlightDate());
    }

    public Place(int row, String place, OrderClass type, boolean free, FlightDate flightDate) {
        this.row = row;
        this.place = place;
        this.type = type;
        this.free = free;
        this.flightDate = flightDate;
    }

    public Place() {

    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public OrderClass getType() {
        return type;
    }

    public void setType(OrderClass type) {
        this.type = type;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getFlightDateId() {
        return flightDate.getId();
    }

    public void setFlightDateId(int flightDateId) {
        this.flightDate.setId(flightDateId);
    }
}
