package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dto.request.FlightAddRequestDTO;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDTO;
import net.thumbtack.airline.dto.request.FlightUpdateRequestDTO;
import net.thumbtack.airline.dto.response.FlightAddResponseDTO;
import net.thumbtack.airline.dto.response.FlightGetResponseAdminDTO;
import net.thumbtack.airline.dto.response.FlightGetResponseDTO;
import net.thumbtack.airline.dto.response.FlightUpdateResponseDTO;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.FlightPeriod;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightDao flightDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setFlightDao(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public FlightAddResponseDTO add(FlightAddRequestDTO request) {
        if (flightDao.exists(request.getFlightName())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.FLIGHT_EXIST_ERROR.toString(), this.getClass().getName(),
                    ErrorCode.FLIGHT_EXIST_ERROR);
        }
        List<String> dates = request.getDates();
        if (dates == null) {
            dates = setDates(request.getSchedule().getFromDate(), request.getSchedule().getToDate(), request.getSchedule().getPeriod());
        }

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
        flightDao.add(flight);
        return new FlightAddResponseDTO(
                flight.getFlightName(),
                flight.getPlaneName(),
                flight.getFromTown(),
                flight.getToTown(),
                flight.getStart(),
                flight.getDuration(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getSchedule(),
                flight.getId(),
                flight.getPlane(),
                flight.isApproved(),
                flight.getDates()
        );
    }

    private void setDays(List<String> dates, FlightPeriod dayOfWeek, LocalDate from, LocalDate to) {
        LocalDate localDate = LocalDate.now();
        for (int i = from.getMonthValue(); i <= to.getMonthValue(); i++) {
            localDate = localDate
                    .withMonth(i)
                    .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.of(dayOfWeek.ordinal())));

            while (localDate.getMonthValue() == i) {
                localDate = localDate.plusWeeks(1);
                dates.add(localDate.toString());
            }
        }
    }

    private List<String> setDates(String getFromDate, String getToDate, String schedulePeriod) {
        List<String> dates;
        LocalDate localDate, dateTo;
        localDate = LocalDate.parse(getFromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateTo = LocalDate.parse(getToDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(!localDate.isBefore(dateTo)) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.INVALID_DATE.toString(),
                    localDate.toString() + " and " + dateTo.toString(), ErrorCode.INVALID_DATE);
        }
        //TODO Is it a good solution?
        //dates = new ArrayList<>(localDate.lengthOfYear()); // max size, mini optimization
        dates = new ArrayList<>();
        String period = schedulePeriod.toUpperCase();

        if (period.equals(FlightPeriod.DAILY.toString())) {
            for (int i = localDate.getDayOfYear(), length = dateTo.getDayOfYear(); i <= length; i++) {
                dates.add(localDate.withDayOfYear(i).toString());
            }
        } else if (period.equals(FlightPeriod.EVEN.toString())) {
            for (int i = localDate.getDayOfYear(), length = dateTo.getDayOfYear(); i <= length; i += 1) {
                localDate = localDate.withDayOfYear(i);
                if (localDate.getDayOfMonth() % 2 == 0) {
                    dates.add(localDate.toString());
                }
            }
        } else if (period.equals(FlightPeriod.ODD.toString())) {
            for (int i = localDate.getDayOfYear(), length = dateTo.getDayOfYear(); i <= length; i += 1) {
                localDate = localDate.withDayOfYear(i);
                if (localDate.getDayOfMonth() % 2 != 0) {
                    dates.add(localDate.toString());
                }
            }
        } else {
            String[] days = period.split(",");
            boolean dayOfWeek;

            for (String s : days) {
                dayOfWeek = false;
                for (FlightPeriod p : FlightPeriod.values()) { // if the day of the week is specified
                    if (s.equals(p.toString())) {
                        setDays(dates, p, localDate, dateTo);
                        dayOfWeek = true;
                    }
                }
                if (!dayOfWeek) { // if the day of the month is specified
                    int dayNumber;
                    try {
                        dayNumber = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        logger.error("Error while parsing integer of day: " + s);
                        throw new BaseException(ConstantsSetting.ErrorsConstants.SIMPLE_ERROR + "parsing date from string",
                                this.getClass().getSimpleName(), ErrorCode.INVALID_DATE_FORMAT);
                    }
                    for (int i = localDate.getMonthValue(), months = dateTo.getMonthValue(); i <= months; i++) {
                        localDate = localDate.withMonth(i);
                        if (dayNumber <= localDate.lengthOfMonth()) {
                            dates.add(localDate.withDayOfMonth(dayNumber).toString());
                        }
                    }
                }
            }
        }
        return dates;
    }

    @Override
    public FlightUpdateResponseDTO update(FlightUpdateRequestDTO request) {
        checkFlightToExist(request.getId());
        List<String> dates = request.getDates();
        if (dates == null) {
            dates = setDates(request.getSchedule().getFromDate(), request.getSchedule().getToDate(), request.getSchedule().getPeriod());
        }
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
                request.isApproved(),
                null,
                request.getId()
        );
        flightDao.update(flight);
        return new FlightUpdateResponseDTO(
                flight.getFlightName(),
                flight.getPlaneName(),
                flight.getFromTown(),
                flight.getToTown(),
                flight.getStart(),
                flight.getDuration(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getSchedule(),
                flight.getId(),
                flight.getPlane(),
                flight.getDates()
        );
    }

    @Override
    public void delete(int id) {
        checkFlightToExist(id);
        flightDao.delete(id);
    }

    @Override
    public FlightAddResponseDTO get(int id) {
        checkFlightToExist(id);
        Flight flight = flightDao.get(id);
        return new FlightAddResponseDTO(
                flight.getFlightName(),
                flight.getPlaneName(),
                flight.getFromTown(),
                flight.getToTown(),
                flight.getStart(),
                flight.getDuration(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getSchedule(),
                flight.getId(),
                flight.getPlane(),
                flight.isApproved(),
                flight.getDates()
        );
    }

    @Override
    public FlightAddResponseDTO approve(int id) {
        checkFlightToExist(id);
        Flight flight = flightDao.approve(id);
        return new FlightAddResponseDTO(
                flight.getFlightName(),
                flight.getPlaneName(),
                flight.getFromTown(),
                flight.getToTown(),
                flight.getStart(),
                flight.getDuration(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getSchedule(),
                flight.getId(),
                flight.getPlane(),
                flight.isApproved(),
                flight.getDates()
        );
    }

    @Override
    public List<FlightGetResponseDTO> get(FlightGetParamsRequestDTO params) {
        if (!params.getFromTown().isEmpty() && params.getToTown().isEmpty()) { // вылетающие из fromTown
            params.setToTown(null);
        } else if (params.getFromTown().isEmpty() && !params.getToTown().isEmpty()) { // прилетающие в town
            params.setFromTown(null);
        } else if (params.getFromTown().isEmpty() && params.getToTown().isEmpty()) { // не указан город
            throw new BaseException(ConstantsSetting.ErrorsConstants.INVALID_REQUEST_DATA.toString(),
                    "Get flights", ErrorCode.INVALID_REQUEST_DATA);
        }
        if (params.getFlightName().isEmpty()) {
            params.setFlightName(null);
        }
        if (params.getPlaneName().isEmpty()) {
            params.setPlaneName(null);
        }
        if (params.getFromDate().isEmpty()) {
            params.setFromDate(null);
        }
        if (params.getToDate().isEmpty()) {
            params.setToDate(null);
        }
        if (params.getFromDate() != null && params.getToDate() != null) { // если указаны даты
            try {
                if (!LocalDate.parse(params.getFromDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(
                        LocalDate.parse(params.getToDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
                    throw new BaseException(ConstantsSetting.ErrorsConstants.INVALID_DATE.toString(),
                            params.getFromDate() + " and " + params.getToDate(), ErrorCode.INVALID_DATE);
                }
            } catch (DateTimeParseException e) {
                throw new BaseException(ConstantsSetting.ErrorsConstants.INVALID_DATE.toString(),
                        params.getFromDate() + " and " + params.getToDate(), ErrorCode.INVALID_DATE);
            }
        }

        List<FlightGetResponseDTO> response = new ArrayList<>();
        flightDao.getAll(
                params.getFlightName(),
                params.getPlaneName(),
                params.getFromTown(),
                params.getToTown(),
                params.getFromDate(),
                params.getToDate()
        ).forEach((e) -> {
            FlightGetResponseDTO flightDto;
            if (params.getUserType().equals(UserRole.ADMIN_ROLE.toString())) {
                flightDto = new FlightGetResponseAdminDTO(
                        e.getFlightName(),
                        e.getPlaneName(),
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
                flightDto = new FlightGetResponseDTO(
                        e.getFlightName(),
                        e.getPlaneName(),
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

    private void checkFlightToExist(int id) {
        if (!flightDao.exists(id)) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.FLIGHT_NOT_FOUND.toString(), this.getClass().getSimpleName(),
                    ErrorCode.FLIGHT_NOT_FOUND);
        }
    }
}
