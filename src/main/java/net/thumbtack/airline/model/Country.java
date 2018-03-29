package net.thumbtack.airline.model;

public class Country {
    private String iso3166;
    private String name;

    public Country(String iso3166, String name) {
        this.iso3166 = iso3166;
        this.name = name;
    }

    public Country() {

    }

    public String getIso3166() {
        return iso3166;
    }

    public void setIso3166(String iso3166) {
        this.iso3166 = iso3166;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
