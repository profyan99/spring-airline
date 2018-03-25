package net.thumbtack.airline.model;

public class Schedule {
    private String fromDate;
    private String toDate;
    private String period;

    public Schedule(String fromDate, String toDate, String period) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.period = period;
    }

    public Schedule() {

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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
