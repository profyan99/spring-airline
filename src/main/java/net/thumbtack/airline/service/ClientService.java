package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.ClientRegistrationRequestDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;

public interface ClientService {
    ClientResponseDTO register(ClientRegistrationRequestDTO request);
}
