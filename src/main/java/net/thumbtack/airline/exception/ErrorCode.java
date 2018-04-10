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
    INVALID_REQUEST_DATA("Invalid request. Please check and send again.", ""),
    ERROR_WITH_DATABASE("Error with ", "Database"),
    NO_ACCESS("You haven't got permissions to get this resource.", ""),
    UNAPPROVED_FLIGHT("Flight didn't approved.", "Flight"),
    ALREADY_APPROVED_FLIGHT("You can't vary flight, because it approved.", "Flight"),
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
