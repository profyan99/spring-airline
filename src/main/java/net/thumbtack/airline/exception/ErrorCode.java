package net.thumbtack.airline.exception;

public enum ErrorCode {
    REGISTRATION_ERROR("Error with registration."),
    ACCOUNT_EXIST_ERROR("Account has already registered."),
    ACCOUNT_NOT_FOUND("Account not found."),
    INVALID_PASSWORD("Invalid password."),
    UNAUTHORISED_ERROR("You haven't logged in."),
    ALREADY_LOGIN("You have already logged in."),
    FLIGHT_EXIST_ERROR("Flight has already created."),
    FLIGHT_NOT_FOUND("Flight not found."),
    INVALID_JSON_FORMAT("You have error in your JSON syntax, please check request data and send again."),
    INVALID_DATE("Invalid value of date."),
    INVALID_REQUEST_DATA("Invalid request. Please check and send again."),
    ERROR_WITH_DATABASE("Error with "),
    NO_ACCESS("You haven't got permissions to get this resource."),
    INVALID_DATE_FORMAT("Invalid date format. Please check and send again.");

    private final String errorCode;

    ErrorCode(String s) {
        this.errorCode = s;
    }

    public String getErrorCodeString() {
        return errorCode;
    }
}
