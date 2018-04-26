package net.thumbtack.airline.model;

public class Plane {
    private String name;
    private int businessRows;
    private int economyRows;
    private int placesInBusinessRow;
    private int placesInEconomyRow;

    public Plane(String name, int businessRows, int economyRows, int placesInBusinessRow, int placesInEconomyRow) {
        this.name = name;
        this.businessRows = businessRows;
        this.economyRows = economyRows;
        this.placesInBusinessRow = placesInBusinessRow;
        this.placesInEconomyRow = placesInEconomyRow;
    }

    public Plane() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBusinessRows() {
        return businessRows;
    }

    public void setBusinessRows(int businessRows) {
        this.businessRows = businessRows;
    }

    public int getEconomyRows() {
        return economyRows;
    }

    public void setEconomyRows(int economyRows) {
        this.economyRows = economyRows;
    }

    public int getPlacesInBusinessRow() {
        return placesInBusinessRow;
    }

    public void setPlacesInBusinessRow(int placesInBusinessRow) {
        this.placesInBusinessRow = placesInBusinessRow;
    }

    public int getPlacesInEconomyRow() {
        return placesInEconomyRow;
    }

    public void setPlacesInEconomyRow(int placesInEconomyRow) {
        this.placesInEconomyRow = placesInEconomyRow;
    }
}
