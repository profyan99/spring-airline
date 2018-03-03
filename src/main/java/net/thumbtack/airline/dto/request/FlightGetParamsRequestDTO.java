package net.thumbtack.airline.dto.request;

public class FlightGetParamsRequestDTO {
    private String fromTown;
    private String toTown;
    private String flightName;
    private String planeName;
    private String fromDate;
    private String toDate;

    public FlightGetParamsRequestDTO(String fromTown, String toTown, String flightName, String planeName, String fromDate, String toDate) {
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.flightName = flightName;
        this.planeName = planeName;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
