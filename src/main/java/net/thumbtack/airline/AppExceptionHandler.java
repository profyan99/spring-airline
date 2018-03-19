package net.thumbtack.airline;

import net.thumbtack.airline.dto.response.ErrorDto;
import net.thumbtack.airline.exception.BaseException;
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

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<?> handleSimpleException(BaseException ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getErrorCode(), ex.getField(), ex.getMessage()));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<ErrorDto> errors = new ArrayList<>();
        result.getFieldErrors().forEach((e) ->
                errors.add(new ErrorDto(e.getCode() + ": " + e.getRejectedValue(), e.getField(), e.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }
}
