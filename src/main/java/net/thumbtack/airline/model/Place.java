package net.thumbtack.airline.model;

public class Place {
    private int row;
    private String place;
    private OrderClass type;
    private boolean free;
    private int flightDateId;

    public Place(int row, String place, OrderClass type) {
        this(row, place, type, true, 0);
    }

    public Place(int row, String place, OrderClass type, boolean free,  int flightDateId) {
        this.row = row;
        this.place = place;
        this.type = type;
        this.free = free;
        this.flightDateId = flightDateId;
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
        return flightDateId;
    }

    public void setFlightDateId(int flightDateId) {
        this.flightDateId = flightDateId;
    }
}
