package net.thumbtack.airline.dto;

import net.thumbtack.airline.dto.response.ErrorDto;

import java.util.List;

public class ErrorResponseDto {
    private List<ErrorDto> errors;

    public ErrorResponseDto(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }
}
