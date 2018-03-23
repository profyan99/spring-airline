package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dto.FlightDTO;
import net.thumbtack.airline.dto.request.FlightAddRequestDTO;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDTO;
import net.thumbtack.airline.dto.response.FlightAddResponseDTO;
import net.thumbtack.airline.dto.response.FlightUpdateResponseDTO;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.FlightPeriod;
import net.thumbtack.airline.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightDao flightDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int MONTH_AMOUNT = 12;

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
            LocalDate localDate = LocalDate.now().withDayOfYear(1); // from the first day of current year, why not
            //TODO Is it a good solution?
            //dates = new ArrayList<>(localDate.lengthOfYear()); // max size, mini optimization
            dates = new ArrayList<>();
            String period = request.getSchedule().getPeriod().toUpperCase();

            if (period.equals(FlightPeriod.DAILY.toString())) {
                for (int i = 1, length = localDate.lengthOfYear(); i < length; i++) {
                    dates.add(localDate.withDayOfYear(i).toString());
                }
            } else if (period.equals(FlightPeriod.EVEN.toString())) {
                for (int i = 2, length = localDate.lengthOfYear(); i < length; i += 2) {
                    dates.add(localDate.withDayOfYear(i).toString());
                }
            } else if (period.equals(FlightPeriod.ODD.toString())) {
                for (int i = 1, length = localDate.lengthOfYear(); i < length; i += 2) {
                    dates.add(localDate.withDayOfYear(i).toString());
                }
            } else {
                String[] days = period.split(",");
                boolean dayOfWeek;

                for (String s : days) {
                    dayOfWeek = false;
                    for (FlightPeriod p : FlightPeriod.values()) { // if the day of the week is specified
                        if (s.equals(p.toString())) {
                            setDays(dates, p);
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
                                    this.getClass().getName(), ErrorCode.INVALID_DATE_FORMAT);
                        }
                        for (int i = 0; i < MONTH_AMOUNT; i++) {
                            localDate = localDate.withMonth(i + 1);
                            if (dayNumber <= localDate.lengthOfMonth()) {
                                dates.add(localDate.withDayOfMonth(dayNumber).toString());
                            }
                        }
                    }
                }
            }
            //dates.trimToSize();
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

    private void setDays(List<String> dates, FlightPeriod dayOfWeek) {
        LocalDate localDate = LocalDate.now();
        for (int i = 1; i <= MONTH_AMOUNT; i++) {
            localDate = localDate
                    .withMonth(i)
                    .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.of(dayOfWeek.ordinal())));

            while (localDate.getMonthValue() == i) {
                localDate = localDate.plusWeeks(1);
                dates.add(localDate.toString());
            }
        }
    }

    @Override
    public FlightUpdateResponseDTO update(FlightDTO request) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public FlightAddResponseDTO get(int id) {
        return null;
    }

    @Override
    public FlightAddResponseDTO approve(int id) {
        return null;
    }

    @Override
    public List<FlightAddResponseDTO> get(FlightGetParamsRequestDTO params) {
        return Collections.emptyList();
    }
}
