package net.thumbtack.airline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BaseException extends RuntimeException {
    private String field;
    private ErrorCode errorCode;

    public BaseException(String message, String field, ErrorCode errorCode) {
        super(message);
        this.field = field;
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode) {
        this(errorCode.getErrorCodeString(), errorCode.getErrorFieldString(), errorCode);
    }

    public BaseException(ErrorCode errorCode, String message) {
        this(message, errorCode.getErrorFieldString(), errorCode);
    }

    public String getField() {
        return field;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
