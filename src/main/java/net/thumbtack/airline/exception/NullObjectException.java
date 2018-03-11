package net.thumbtack.airline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NullObjectException extends RuntimeException {
    public NullObjectException(String message) {
        super(message);
    }
}
