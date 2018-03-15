package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.ClientRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDTO;

public interface ClientService {
    ClientResponseDTO register(ClientRegistrationRequestDTO request);
    ClientUpdateResponseDTO update(ClientUpdateRequestDTO request);
}
