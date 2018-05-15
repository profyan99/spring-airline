package net.thumbtack.airline.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightDate {
    private int id;
    private Flight flight;
    private LocalDate date;
    private int freeEconomyPlaces;
    private int freeBusinessPlaces;
    private List<Place> places;

    public FlightDate(int id, Flight flight, LocalDate date, int freeEconomyPlaces, int freeBusinessPlaces, List<Place> places) {
        this.id = id;
        this.flight = flight;
        this.date = date;
        this.freeEconomyPlaces = freeEconomyPlaces;
        this.freeBusinessPlaces = freeBusinessPlaces;
        this.places = places;
    }

    public FlightDate(LocalDate date, int freeEconomyPlaces, int freeBusinessPlaces, List<Place> places) {
        this(0, new Flight(), date, freeEconomyPlaces, freeBusinessPlaces, places);
    }

    public FlightDate(int id, Flight flight, LocalDate date, int freeEconomyPlaces, int freeBusinessPlaces) {
        this(id, flight, date, freeEconomyPlaces, freeBusinessPlaces, new ArrayList<>());
    }

    public FlightDate() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getFreeEconomyPlaces() {
        return freeEconomyPlaces;
    }

    public void setFreeEconomyPlaces(int freeEconomyPlaces) {
        this.freeEconomyPlaces = freeEconomyPlaces;
    }

    public int getFreeBusinessPlaces() {
        return freeBusinessPlaces;
    }

    public void setFreeBusinessPlaces(int freeBusinessPlaces) {
        this.freeBusinessPlaces = freeBusinessPlaces;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
