package com.thumbtack.airline.service;

import com.thumbtack.airline.dto.request.ClientRegistrationRequestDTO;
import com.thumbtack.airline.dto.response.ClientResponseDTO;

public interface ClientService {
    ClientResponseDTO register(ClientRegistrationRequestDTO request);
}
