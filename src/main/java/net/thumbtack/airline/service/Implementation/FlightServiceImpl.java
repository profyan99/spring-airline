package net.thumbtack.airline.service.Implementation;

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
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

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
            throw new BaseException(ErrorCode.FLIGHT_EXIST_ERROR.getErrorCodeString(),
                    ErrorCode.FLIGHT_EXIST_ERROR.getErrorFieldString(), ErrorCode.FLIGHT_EXIST_ERROR);
        }
        List<String> dates = request.getDates();
        if (dates == null) {
            dates = setDates(request.getSchedule().getFromDate(), request.getSchedule().getToDate(), request.getSchedule().getPeriod());
        }
        Plane plane = flightDao.getPlane(request.getPlaneName());
        List<Place> places = new ArrayList<>();
        setPlaces(plane, places);

        Flight flight = new Flight(
                request.getFlightName(),
                request.getPlaneName(),
                request.getFromTown(),
                request.getToTown(),
                request.getStart(),
                request.getDuration(),
                request.getPriceBusiness(),
                request.getPriceEconomy(),
                request.getSchedule(),
                dates,
                false,
                null
        );
        flightDao.add(flight, places);

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

    private static void setPlaces(Plane plane, List<Place> places) {
        PlaceName[] placeNames = PlaceName.values();

        for (int i = 1; i <= plane.getBussinesRows(); i++) {
            for (int j = 0; j < plane.getPlacesInBusinessRow(); j++) {
                places.add(new Place(i, placeNames[j].name(), OrderClass.BUSINESS));
            }
        }
        for (int i = plane.getBussinesRows()+1; i <= plane.getBussinesRows() + plane.getEconomyRows(); i++) {
            for (int j = 0; j < plane.getPlacesInEconomyRow(); j++) {
                places.add(new Place(i, placeNames[j].name(), OrderClass.ECONOMY));
            }
        }
    }

    private static void setDays(List<String> dates, FlightPeriod dayOfWeek, LocalDate from, LocalDate to) {
        LocalDate localDate = LocalDate.from(from);
        for (int i = from.getMonthValue(); i <= to.getMonthValue(); i++) {
            localDate = localDate
                    .withMonth(i)
                    .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.of(dayOfWeek.ordinal())));

            while ((i != to.getMonthValue() && localDate.getMonthValue() == i) ||
                    (i == to.getMonthValue() && localDate.getDayOfMonth() <= to.getDayOfMonth())) {
                localDate = localDate.plusWeeks(1);
                dates.add(localDate.toString());
            }
        }
    }

    private static List<String> setDates(String getFromDate, String getToDate, String schedulePeriod) {
        List<String> dates;
        LocalDate dateFrom, dateTo;
        dateFrom = LocalDate.parse(getFromDate, DateTimeFormatter.ofPattern(Utils.DATE_PATTERN));
        dateTo = LocalDate.parse(getToDate, DateTimeFormatter.ofPattern(Utils.DATE_PATTERN));
        if (!dateFrom.isBefore(dateTo)) {
            throw new BaseException(ErrorCode.INVALID_DATE.getErrorCodeString(),
                    dateFrom.toString() + " and " + dateTo.toString(), ErrorCode.INVALID_DATE);
        }
        dates = new ArrayList<>(dateTo.getDayOfYear() - dateFrom.getDayOfYear() + 1);
        String period = schedulePeriod.toUpperCase();

        switch (FlightPeriod.getValue(period)) {
            case DAILY: {
                for (int i = dateFrom.getDayOfYear(), length = dateTo.getDayOfYear(); i <= length; i++) {
                    dates.add(dateFrom.withDayOfYear(i).toString());
                }
                break;
            }
            case EVEN: {
                for (int i = dateFrom.getDayOfYear(), length = dateTo.getDayOfYear(); i <= length; i++) {
                    dateFrom = dateFrom.withDayOfYear(i);
                    if (dateFrom.getDayOfMonth() % 2 == 0) {
                        dates.add(dateFrom.toString());
                    }
                }
                break;
            }
            case ODD: {
                for (int i = dateFrom.getDayOfYear(), length = dateTo.getDayOfYear(); i <= length; i++) {
                    dateFrom = dateFrom.withDayOfYear(i);
                    if (dateFrom.getDayOfMonth() % 2 != 0) {
                        dates.add(dateFrom.toString());
                    }
                }
                break;
            }
            default: {
                String[] days;
                try {
                    days = period.split(",");
                } catch (PatternSyntaxException e) {
                    logger.error("Error while parsing days of period");
                    throw new BaseException(ErrorCode.INVALID_DATE.getErrorCodeString(),
                            ErrorCode.INVALID_DATE.getErrorFieldString(), ErrorCode.INVALID_DATE);
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
                            throw new BaseException(ErrorCode.INVALID_DATE.getErrorCodeString(),
                                    ErrorCode.INVALID_DATE_FORMAT.getErrorFieldString(), ErrorCode.INVALID_DATE_FORMAT);
                        }
                        for (int i = dateFrom.getMonthValue(), months = dateTo.getMonthValue(); i <= months; i++) {
                            dateFrom = dateFrom.withMonth(i);
                            if ((i == dateTo.getMonthValue() && dayNumber <= dateTo.getDayOfMonth())
                                    || (i != dateTo.getMonthValue() && dayNumber <= dateFrom.lengthOfMonth())) {
                                dates.add(dateFrom.withDayOfMonth(dayNumber).toString());
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
        List<String> dates = request.getDates();
        if (dates == null) {
            dates = setDates(request.getSchedule().getFromDate(), request.getSchedule().getToDate(), request.getSchedule().getPeriod());
        }
        Plane plane = flightDao.getPlane(request.getPlaneName());
        List<Place> places = new ArrayList<>();
        setPlaces(plane, places);

        Flight flight = new Flight(
                request.getFlightName(),
                request.getPlaneName(),
                request.getFromTown(),
                request.getToTown(),
                request.getStart(),
                request.getDuration(),
                request.getPriceBusiness(),
                request.getPriceEconomy(),
                request.getSchedule(),
                dates,
                false,
                null,
                request.getId()
        );
        flightDao.update(flight, places);
        return new FlightUpdateResponseDto(
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
    public void delete(int id) {
        checkFlightApproves(id);
        flightDao.delete(id);
    }

    @Override
    public FlightAddResponseDto get(int id) {
        checkFlightExists(id);
        Flight flight = flightDao.get(id);
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
            } else {
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
            }
            response.add(flightDto);
        });
        return response;
    }

    private void checkFlightExists(int id) {
        if (!flightDao.exists(id)) {
            throw new BaseException(ErrorCode.FLIGHT_NOT_FOUND.getErrorCodeString(),
                    ErrorCode.FLIGHT_NOT_FOUND.getErrorFieldString(), ErrorCode.FLIGHT_NOT_FOUND);
        }
    }

    private void checkFlightApproves(int id) {
        checkFlightExists(id);
        if (flightDao.get(id).isApproved()) {
            throw new BaseException(ErrorCode.ALREADY_APPROVED_FLIGHT.getErrorCodeString(),
                    ErrorCode.ALREADY_APPROVED_FLIGHT.getErrorFieldString(), ErrorCode.ALREADY_APPROVED_FLIGHT);
        }
    }
}
