package net.thumbtack.airline.exception;

public enum ErrorCode {
    REGISTRATION_ERROR("Error with registration.", "Database"),
    ACCOUNT_EXIST_ERROR("Account has already registered.", "Registration"),
    ACCOUNT_NOT_FOUND("Account not found.", "Account"),
    INVALID_PASSWORD("Invalid password.", "Password"),
    UNAUTHORISED_ERROR("You haven't logged in.", "Authorization"),
    ALREADY_LOGIN("You have already logged in.", "Authorization"),
    FLIGHT_EXIST_ERROR("Flight has already created.", "Flight"),
    FLIGHT_NOT_FOUND("Flight not found.", "Flight"),
    INVALID_JSON_FORMAT("You have error in your JSON syntax, please check request data and send again.", ""),
    INVALID_DATE("Invalid value of date.", "Date"),
    EXPECTED_FLIGHT_NAME_TO_TOWN_FROM_TOWN("Expected flightName or toTown, or fromTown, but not found.", "Params"),
    ERROR_WITH_DATABASE("Error with ", "Database"),
    NO_ACCESS("You haven't got permissions to getUser this resource.", ""),
    UNAPPROVED_FLIGHT("Flight didn't approved.", "Flight"),
    ALREADY_APPROVED_FLIGHT("You can't vary flight, because it approved.", "Flight"),
    PASSENGER_NOT_FOUND("Passenger not found.", "Passenger"),
    INVALID_PLACE("Invalid value of place.", "Place"),
    ORDER_NOT_FOUND("Order not found.", "Order"),
    PLACE_OCCUPIED("Place has already occupied. Please choose another one.", "Place"),
    PLANE_NOT_FOUND("Plane not found.", "Plane"),
    PLACE_NOT_FOUND("Place not found.", "Place"),
    NO_AVAILABLE_ECONOMY_PLACES("There are no free economy seats on this flight.", "Place"),
    NO_AVAILABLE_BUSINESS_PLACES("There are no free business seats on this flight.", "Place"),
    EXPECTED_SCHEDULE_OR_DATES("Expected schedule or dates, but not found.", "Flight"),
    FLIGHT_WRONG_PRICE("Wrong price in flight", "Flight"),
    INVALID_NATIONALITY("Nationality is invalid.", "Passenger"),
    INVALID_ORDER_ID_FOR_CLIENT("Order for user not found.", "Order"),
    PASSENGER_ALREADY_REGISTERED("Passenger already registered.", "Passenger"),
    INVALID_DATE_FORMAT("Invalid date format. Please check and send again.", "Date");

    private final String
            errorCode,
            errorField;

    ErrorCode(String s, String f) {
        this.errorCode = s;
        this.errorField = f;
    }

    public String getErrorCodeString() {
        return errorCode;
    }

    public String getErrorFieldString() {
        return errorField;
    }
}
