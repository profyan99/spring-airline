package net.thumbtack.airline.model;

public class Place {
    private int row;
    private String place;
    private OrderClass type;
    private boolean free;
    private String date;
    private int flightId;

    public Place(int row, String place, OrderClass type) {
        this(row, place, type, true, "", 0);
    }

    public Place(int row, String place, OrderClass type, boolean free, String date, int flightId) {
        this.row = row;
        this.place = place;
        this.type = type;
        this.free = free;
        this.date = date;
        this.flightId = flightId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}
