package net.thumbtack.airline.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightDate {
    private int id;
    private int flightId;
    private LocalDate date;
    private int freePlaces;
    private List<Place> places;

    public FlightDate(int id, int flightId, LocalDate date, int freePlaces, List<Place> places) {
        this.id = id;
        this.flightId = flightId;
        this.date = date;
        this.freePlaces = freePlaces;
        this.places = places;
    }

    public FlightDate(LocalDate date, int freePlaces, List<Place> places) {
        this(0, 0, date, freePlaces, places);
    }

    public FlightDate(int id, int flightId, LocalDate date, int freePlaces) {
        this(id, flightId, date, freePlaces, new ArrayList<>());
    }

    public FlightDate() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
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
