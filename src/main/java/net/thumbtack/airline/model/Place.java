package net.thumbtack.airline.model;

public class Place {
    private int row;
    private String place;
    private OrderClass type;

    public Place(int row, String place, OrderClass type) {
        this.row = row;
        this.place = place;
        this.type = type;
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
}
