package net.thumbtack.airline.model;

public class Plane {
    private String name;
    private int bussinesRows;
    private int economyRows;
    private int placesInBusinessRow;
    private int placesInEconomyRow;

    public Plane(String name, int bussinesRows, int economyRows, int placesInBusinessRow, int placesInEconomyRow) {
        this.name = name;
        this.bussinesRows = bussinesRows;
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

    public int getBussinesRows() {
        return bussinesRows;
    }

    public void setBussinesRows(int bussinesRows) {
        this.bussinesRows = bussinesRows;
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
