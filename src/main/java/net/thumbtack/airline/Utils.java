package net.thumbtack.airline;

import net.thumbtack.airline.dto.request.FlightGetParamsRequestDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static net.thumbtack.airline.exception.ErrorCode.EXPECTED_FLIGHT_NAME_TO_TOWN_FROM_TOWN;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:constants.properties")
public class Utils {

    @Value("${min_password_length}")
    private int minPasswordLength;

    @Value("${max_name_length}")
    private int maxNameLength;

    @Value("${rest_http_port}")
    private String restHttpPort;

    public static final String BAD_FIRSTNAME = "Bad firstName";
    public static final String BAD_LASTNAME = "Bad lastName";
    public static final String BAD_PATRONYMIC = "Bad patronymic";
    public static final String BAD_LOGIN = "Bad login";
    public static final String BAD_PASSWORD = "Bad password";
    public static final String BAD_PHONE = "Bad phone";
    public static final String BAD_EMAIL = "Bad email";
    public static final String BAD_PASSPORT = "Bad passport";

    public static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    public static final String NAME_REGEX = "^[а-яА-ЯёЁ\\s\\-]+$";
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public int getMinPasswordLength() {

        return minPasswordLength;
    }

    public int getMaxNameLength() {
        return maxNameLength;
    }

    public String getRestHttpPort() {
        return restHttpPort;
    }

    public static void validateRequestParams(FlightGetParamsRequestDto params) {
        if (!params.getFromTown().isEmpty() && params.getToTown().isEmpty()) { // вылетающие из fromTown
            params.setToTown(null);
        } else if (params.getFromTown().isEmpty() && !params.getToTown().isEmpty()) { // прилетающие в town
            params.setFromTown(null);
        } else if (params.getFromTown().isEmpty() && params.getToTown().isEmpty()) {
            if (params.getFlightName().isEmpty()) {
                throw new BaseException(EXPECTED_FLIGHT_NAME_TO_TOWN_FROM_TOWN);    // не указан город и название
            } else {
                params.setFromTown(null);
                params.setToTown(null);
            }
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
                if (!LocalDate.parse(params.getFromDate(), DateTimeFormatter.ofPattern(DATE_PATTERN)).isBefore(
                        LocalDate.parse(params.getToDate(), DateTimeFormatter.ofPattern(DATE_PATTERN)))) {
                    throw new BaseException(ErrorCode.INVALID_DATE.getErrorCodeString(),
                            params.getFromDate() + " and " + params.getToDate(), ErrorCode.INVALID_DATE);
                }
            } catch (DateTimeParseException e) {
                throw new BaseException(ErrorCode.INVALID_DATE.getErrorCodeString(),
                        params.getFromDate() + " and " + params.getToDate(), ErrorCode.INVALID_DATE);
            }
        }
    }
}
