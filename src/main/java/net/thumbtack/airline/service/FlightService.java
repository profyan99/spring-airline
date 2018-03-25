package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.FlightAddRequestDTO;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDTO;
import net.thumbtack.airline.dto.request.FlightUpdateRequestDTO;
import net.thumbtack.airline.dto.response.FlightAddResponseDTO;
import net.thumbtack.airline.dto.response.FlightGetResponseDTO;
import net.thumbtack.airline.dto.response.FlightUpdateResponseDTO;

import java.util.List;

public interface FlightService {
    FlightAddResponseDTO add(FlightAddRequestDTO request);

    FlightUpdateResponseDTO update(FlightUpdateRequestDTO request);

    void delete(int id);

    FlightAddResponseDTO get(int id);

    List<FlightGetResponseDTO> get(FlightGetParamsRequestDTO params);

    FlightAddResponseDTO approve(int id);


}
