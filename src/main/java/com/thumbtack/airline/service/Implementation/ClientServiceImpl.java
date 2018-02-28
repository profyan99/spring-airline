package com.thumbtack.airline.service.Implementation;

import com.thumbtack.airline.dto.request.ClientRegistrationRequestDTO;
import com.thumbtack.airline.dto.response.ClientResponseDTO;
import com.thumbtack.airline.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public ClientResponseDTO register(ClientRegistrationRequestDTO request) {
        return null;
    }
}
