package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.FlightAddRequestDto;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDto;
import net.thumbtack.airline.dto.request.FlightUpdateRequestDto;
import net.thumbtack.airline.dto.response.FlightAddResponseDto;
import net.thumbtack.airline.dto.response.FlightGetResponseDto;
import net.thumbtack.airline.dto.response.FlightUpdateResponseDto;

import java.util.List;

public interface FlightService {
    FlightAddResponseDto add(FlightAddRequestDto request);

    FlightUpdateResponseDto update(FlightUpdateRequestDto request);

    void delete(int id);

    FlightAddResponseDto get(int id);

    List<FlightGetResponseDto> get(FlightGetParamsRequestDto params);

    FlightAddResponseDto approve(int id);


}
