package net.thumbtack.airline;

import net.thumbtack.airline.dto.response.ErrorDTO;
import net.thumbtack.airline.exception.SimpleException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SimpleException.class})
    public ResponseEntity<?> handleSimpleException(SimpleException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(ex.getErrorCode(), ex.getField(), ex.getMessage()));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<ErrorDTO> errors = new ArrayList<>();
        result.getFieldErrors().forEach((e) ->
                errors.add(new ErrorDTO(e.getCode() + ": " + e.getRejectedValue(), e.getField(), e.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }
}
