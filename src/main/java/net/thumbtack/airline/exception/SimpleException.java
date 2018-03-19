package net.thumbtack.airline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
// REVU rename :-)
public class SimpleException extends RuntimeException {
    private String field;
    // REVU it is better to have here ErrorCode instead of it's string
    private String errorCode;

    public SimpleException(String message, String field, String errorCode) {
        super(message);
        this.field = field;
        this.errorCode = errorCode;
    }

    public String getField() {
        return field;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
