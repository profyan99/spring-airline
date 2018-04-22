package net.thumbtack.airline.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.thumbtack.airline.Utils;

import java.time.LocalDate;

public class Schedule {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.DATE_PATTERN)
    private LocalDate fromDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.DATE_PATTERN)
    private LocalDate toDate;
    private String period;

    public Schedule(LocalDate fromDate, LocalDate toDate, String period) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.period = period;
    }

    public Schedule() {

    }


    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
