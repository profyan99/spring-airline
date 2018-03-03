package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.FlightAddRequestDTO;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDTO;
import net.thumbtack.airline.dto.response.FlightAddResponseDTO;
import net.thumbtack.airline.dto.response.FlightUpdateResponseDTO;

import java.util.List;

public interface FlightService {
    FlightAddResponseDTO add(FlightAddRequestDTO request);

    FlightUpdateResponseDTO update(FlightAddRequestDTO request);

    boolean delete(int id);

    FlightAddResponseDTO get(int id);

    List<FlightAddResponseDTO> get(FlightGetParamsRequestDTO params);

    FlightAddResponseDTO approve(int id);


}
