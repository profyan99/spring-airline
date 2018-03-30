package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.ClientRegistrationRequestDto;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDto;

public interface ClientService {
    ClientResponseDto register(ClientRegistrationRequestDto request);

    ClientUpdateResponseDto update(ClientUpdateRequestDto request);
}
