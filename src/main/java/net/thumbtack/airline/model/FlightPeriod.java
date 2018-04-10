package net.thumbtack.airline.model;

public enum FlightPeriod {
    //days of the week
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT,
    SUN,

    // types
    DAILY,
    ODD,
    EVEN,
    ANOTHER;

    public static FlightPeriod getValue(String value) {
        FlightPeriod period;
        try {
            period = FlightPeriod.valueOf(value);
        } catch (IllegalArgumentException e) {
            period = ANOTHER;
        }
        return period;
    }

}
