package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.FlightAddRequestDTO;
import net.thumbtack.airline.dto.response.FlightAddResponseDTO;

public interface FlightService {
    FlightAddResponseDTO add(FlightAddRequestDTO request);
}
