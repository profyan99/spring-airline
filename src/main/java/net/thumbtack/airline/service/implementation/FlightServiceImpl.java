package net.thumbtack.airline.service.implementation;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dto.request.FlightAddRequestDto;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDto;
import net.thumbtack.airline.dto.request.FlightUpdateRequestDto;
import net.thumbtack.airline.dto.response.FlightAddResponseDto;
import net.thumbtack.airline.dto.response.FlightGetResponseAdminDto;
import net.thumbtack.airline.dto.response.FlightGetResponseDto;
import net.thumbtack.airline.dto.response.FlightUpdateResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.*;
import net.thumbtack.airline.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import static net.thumbtack.airline.exception.ErrorCode.*;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightDao flightDao;

    private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

    @Autowired
    public void setFlightDao(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public FlightAddResponseDto add(FlightAddRequestDto request) {
        if (flightDao.exists(request.getFlightName())) {
            throw new BaseException(FLIGHT_EXIST_ERROR);
        }
        List<LocalDate> dates = request.getDates();
        if (dates == null && request.getSchedule() == null) {
            throw new BaseException(EXPECTED_SCHEDULE_OR_DATES);
        }
        if (request.getPriceBusiness() <= 0 || request.getPriceEconomy() <= 0) {
            throw new BaseException(FLIGHT_WRONG_PRICE);
        }
        if (dates == null) {
            dates = setDates(request.getSchedule().getFromDate(), request.getSchedule().getToDate(), request.getSchedule().getPeriod());
        }
        Plane plane = flightDao.getPlane(request.getPlaneName()).orElseThrow(() ->
                new BaseException(PLANE_NOT_FOUND)
        );
        List<Place> places = new ArrayList<>();
        setPlaces(plane, places);
        List<FlightDate> flightDates = new ArrayList<>(dates.size());
        for (LocalDate date : dates) {
            flightDates.add(new FlightDate(
                    date,
                    plane.getEconomyRows() * plane.getPlacesInEconomyRow(),
                    plane.getBusinessRows() * plane.getPlacesInBusinessRow(),
                    places
            ));
        }

        Flight flight = new Flight(
                request.getFlightName(),
                request.getFromTown(),
                request.getToTown(),
                request.getStart(),
                request.getDuration(),
                request.getPriceBusiness(),
                request.getPriceEconomy(),
                request.getSchedule(),
                flightDates,
                false,
                plane
        );
        flightDao.add(flight);

        return new FlightAddResponseDto(
                flight.getFlightName(),
                flight.getFromTown(),
                flight.getToTown(),
                flight.getStart(),
                flight.getDuration(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getSchedule(),
                dates,
                flight.getId(),
                flight.getPlane(),
                flight.isApproved()
        );
    }

    private static void setPlaces(Plane plane, List<Place> places) {
        for (int i = 1; i <= plane.getBusinessRows(); i++) {
            for (int j = 0; j < plane.getPlacesInBusinessRow(); j++) {
                places.add(new Place(i, Character.toString((char) ('A' + j)), OrderClass.BUSINESS));
            }
        }
        for (int i = plane.getBusinessRows() + 1; i <= plane.getBusinessRows() + plane.getEconomyRows(); i++) {
            for (int j = 0; j < plane.getPlacesInEconomyRow(); j++) {
                places.add(new Place(i, Character.toString((char) ('A' + j)), OrderClass.ECONOMY));
            }
        }
    }

    private static void setDays(List<LocalDate> dates, FlightPeriod dayOfWeek, LocalDate from, LocalDate to) {
        DayOfWeek convertedDayOfWeek = DayOfWeek.of(dayOfWeek.ordinal() + 1);
        LocalDate firstWeekDay = from.with(TemporalAdjusters.nextOrSame(convertedDayOfWeek));

        while (firstWeekDay.isBefore(to) || firstWeekDay.isEqual(to)) {
            dates.add(firstWeekDay);
            firstWeekDay = firstWeekDay.with(TemporalAdjusters.next(convertedDayOfWeek));
        }
    }

    private static List<LocalDate> setDates(LocalDate getFromDate, LocalDate getToDate, String schedulePeriod) {
        List<LocalDate> dates;
        LocalDate dateFrom, dateTo;
        dateFrom = LocalDate.from(getFromDate);
        dateTo = LocalDate.from(getToDate);
        if (!dateFrom.isBefore(dateTo)) {
            throw new BaseException(ErrorCode.INVALID_DATE.getErrorCodeString(),
                    dateFrom.toString() + " and " + dateTo.toString(), ErrorCode.INVALID_DATE);
        }
        dates = new ArrayList<>(dateTo.getDayOfYear() - dateFrom.getDayOfYear() + 1);
        String period = schedulePeriod.toUpperCase();

        switch (FlightPeriod.getValue(period)) {
            case DAILY: {
                for (int i = dateFrom.getDayOfYear(), length = dateTo.getDayOfYear(); i <= length; i++) {
                    dates.add(dateFrom.withDayOfYear(i));
                }
                break;
            }
            case EVEN: {
                for (int i = dateFrom.getDayOfYear(), length = dateTo.getDayOfYear(); i <= length; i++) {
                    dateFrom = dateFrom.withDayOfYear(i);
                    if (dateFrom.getDayOfMonth() % 2 == 0) {
                        dates.add(dateFrom);
                    }
                }
                break;
            }
            case ODD: {
                for (int i = dateFrom.getDayOfYear(), length = dateTo.getDayOfYear(); i <= length; i++) {
                    dateFrom = dateFrom.withDayOfYear(i);
                    if (dateFrom.getDayOfMonth() % 2 != 0) {
                        dates.add(dateFrom);
                    }
                }
                break;
            }
            default: {
                String[] days;
                try {
                    days = period.split("\\s*,\\s*");
                } catch (PatternSyntaxException e) {
                    logger.error("Error while parsing days of period");
                    throw new BaseException(INVALID_DATE_FORMAT);
                }
                boolean dayOfWeek;
                for (String s : days) {
                    dayOfWeek = false;
                    for (FlightPeriod p : FlightPeriod.values()) { // if the day of the week is specified
                        if (s.equals(p.toString())) {
                            setDays(dates, p, dateFrom, dateTo);
                            dayOfWeek = true;
                        }
                    }
                    if (!dayOfWeek) { // if the day of the month is specified
                        int dayNumber;
                        try {
                            dayNumber = Integer.parseInt(s);
                        } catch (NumberFormatException e) {
                            logger.error("Error while parsing integer of day: " + s);
                            throw new BaseException(INVALID_DATE_FORMAT);
                        }
                        LocalDate tempDate = LocalDate.from(dateFrom);
                        for (int i = dateFrom.getMonthValue(), months = dateTo.getMonthValue(); i <= months; i++) {
                            tempDate = LocalDate.of(tempDate.getYear(), i, 1);

                            if (((i == dateTo.getMonthValue() && dayNumber <= dateTo.getDayOfMonth())
                                    || (i == dateFrom.getMonthValue() && dayNumber >= dateFrom.getDayOfMonth())
                                    || (i != dateTo.getMonthValue() && i != dateFrom.getMonthValue()))
                                    && dayNumber <= tempDate.lengthOfMonth()) {
                                dates.add(tempDate.withDayOfMonth(dayNumber));
                            }
                        }
                    }
                }
            }
        }
        return dates;
    }

    @Override
    public FlightUpdateResponseDto update(FlightUpdateRequestDto request) {
        checkFlightApproves(request.getId());
        List<LocalDate> dates = request.getDates();
        if (dates == null && request.getSchedule() == null) {
            throw new BaseException(EXPECTED_SCHEDULE_OR_DATES);
        }
        if (request.getPriceBusiness() <= 0 || request.getPriceEconomy() <= 0) {
            throw new BaseException(FLIGHT_WRONG_PRICE);
        }
        if (dates == null) {
            dates = setDates(request.getSchedule().getFromDate(), request.getSchedule().getToDate(), request.getSchedule().getPeriod());
        }
        Plane plane = flightDao.getPlane(request.getPlaneName()).orElseThrow(() ->
                new BaseException(PLANE_NOT_FOUND)
        );
        List<Place> places = new ArrayList<>();
        setPlaces(plane, places);
        List<FlightDate> flightDates = new ArrayList<>(dates.size());
        for (LocalDate date : dates) {
            flightDates.add(new FlightDate(
                    date,
                    plane.getEconomyRows() * plane.getPlacesInEconomyRow(),
                    plane.getBusinessRows() * plane.getPlacesInBusinessRow(),
                    places
            ));
        }

        Flight flight = new Flight(
                request.getFlightName(),
                request.getFromTown(),
                request.getToTown(),
                request.getStart(),
                request.getDuration(),
                request.getPriceBusiness(),
                request.getPriceEconomy(),
                request.getSchedule(),
                flightDates,
                false,
                plane,
                request.getId()
        );
        flightDao.update(flight);
        return new FlightUpdateResponseDto(
                flight.getFlightName(),
                flight.getFromTown(),
                flight.getToTown(),
                flight.getStart(),
                flight.getDuration(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getSchedule(),
                dates,
                flight.getId(),
                flight.getPlane(),
                flight.isApproved()
        );
    }

    @Override
    public void delete(int id) {
        checkFlightApproves(id);
        flightDao.delete(id);
    }

    @Override
    public FlightAddResponseDto get(int id) {
        checkFlightExists(id);
        Flight flight = flightDao.get(id).orElseThrow(() ->
                new BaseException(FLIGHT_NOT_FOUND)
        );
        return new FlightAddResponseDto(
                flight.getFlightName(),
                flight.getFromTown(),
                flight.getToTown(),
                flight.getStart(),
                flight.getDuration(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getSchedule(),
                flight.getDates(),
                flight.getId(),
                flight.getPlane(),
                flight.isApproved()
        );
    }

    @Override
    public FlightAddResponseDto approve(int id) {
        checkFlightApproves(id);
        Flight flight = flightDao.approve(id);
        return new FlightAddResponseDto(
                flight.getFlightName(),
                flight.getFromTown(),
                flight.getToTown(),
                flight.getStart(),
                flight.getDuration(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getSchedule(),
                flight.getDates(),
                flight.getId(),
                flight.getPlane(),
                flight.isApproved()
        );
    }

    @Override
    public List<FlightGetResponseDto> get(FlightGetParamsRequestDto params) {
        Utils.validateRequestParams(params);

        List<FlightGetResponseDto> response = new ArrayList<>();
        flightDao.getAll(
                params.getFlightName(),
                params.getPlaneName(),
                params.getFromTown(),
                params.getToTown(),
                params.getFromDate(),
                params.getToDate()
        ).forEach((e) -> {
            FlightGetResponseDto flightDto;
            if (params.getUserType().equals(UserRole.ADMIN)) {
                flightDto = new FlightGetResponseAdminDto(
                        e.getFlightName(),
                        e.getFromTown(),
                        e.getToTown(),
                        e.getStart(),
                        e.getDuration(),
                        e.getPriceBusiness(),
                        e.getPriceEconomy(),
                        e.getSchedule(),
                        e.getDates(),
                        e.getId(),
                        e.getPlane(),
                        e.isApproved()
                );
                response.add(flightDto);
            } else {
                if (e.isApproved()) {
                    flightDto = new FlightGetResponseDto(
                            e.getFlightName(),
                            e.getFromTown(),
                            e.getToTown(),
                            e.getStart(),
                            e.getDuration(),
                            e.getPriceBusiness(),
                            e.getPriceEconomy(),
                            e.getSchedule(),
                            e.getDates(),
                            e.getId()
                    );
                    response.add(flightDto);
                }
            }

        });
        return response;
    }

    private void checkFlightExists(int id) {
        if (!flightDao.exists(id)) {
            throw new BaseException(FLIGHT_NOT_FOUND);
        }
    }

    private void checkFlightApproves(int id) {
        checkFlightExists(id);
        Flight flight = flightDao.get(id).orElseThrow(() ->
                new BaseException(FLIGHT_NOT_FOUND)
        );
        if (flight.isApproved()) {
            throw new BaseException(ALREADY_APPROVED_FLIGHT);
        }
    }
}
