package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dto.FlightDTO;
import net.thumbtack.airline.dto.request.FlightAddRequestDTO;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDTO;
import net.thumbtack.airline.dto.response.FlightAddResponseDTO;
import net.thumbtack.airline.dto.response.FlightUpdateResponseDTO;
import net.thumbtack.airline.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Override
    public FlightAddResponseDTO add(FlightAddRequestDTO request) {
        return null;
    }

    @Override
    public FlightUpdateResponseDTO update(FlightDTO request) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public FlightAddResponseDTO get(int id) {
        return null;
    }

    @Override
    public FlightAddResponseDTO approve(int id) {
        return null;
    }

    @Override
    public List<FlightAddResponseDTO> get(FlightGetParamsRequestDTO params) {
        return Collections.emptyList();
    }
}
