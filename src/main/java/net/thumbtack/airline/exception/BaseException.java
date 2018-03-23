package net.thumbtack.airline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BaseException extends RuntimeException {
    private String field;
    //TODO make enum with code errors
    private ErrorCode errorCode;

    public BaseException(String message, String field, ErrorCode errorCode) {
        super(message);
        this.field = field;
        this.errorCode = errorCode;
    }

    public String getField() {
        return field;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
