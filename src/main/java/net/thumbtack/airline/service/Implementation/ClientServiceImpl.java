package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dto.request.ClientRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDTO;
import net.thumbtack.airline.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public ClientResponseDTO register(ClientRegistrationRequestDTO request) {
        return null;
    }

    @Override
    public ClientUpdateResponseDTO update(ClientUpdateRequestDTO request) {
        return null;
    }
}
