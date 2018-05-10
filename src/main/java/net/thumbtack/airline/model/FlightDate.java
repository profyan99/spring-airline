package net.thumbtack.airline.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightDate {
    private int id;
    private Flight flight;
    private LocalDate date;
    private int freePlaces;
    private List<Place> places;

    public FlightDate(int id, Flight flight, LocalDate date, int freePlaces, List<Place> places) {
        this.id = id;
        this.flight = flight;
        this.date = date;
        this.freePlaces = freePlaces;
        this.places = places;
    }

    public FlightDate(LocalDate date, int freePlaces, List<Place> places) {
        this(0, new Flight(), date, freePlaces, places);
    }

    public FlightDate(int id, Flight flight, LocalDate date, int freePlaces) {
        this(id, flight, date, freePlaces, new ArrayList<>());
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

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
