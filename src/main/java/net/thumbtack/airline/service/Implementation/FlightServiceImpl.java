package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dto.request.FlightAddRequestDTO;
import net.thumbtack.airline.dto.response.FlightAddResponseDTO;
import net.thumbtack.airline.service.FlightService;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {
    @Override
    public FlightAddResponseDTO add(FlightAddRequestDTO request) {
        return null;
    }
}
