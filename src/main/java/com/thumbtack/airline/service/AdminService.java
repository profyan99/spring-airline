package com.thumbtack.airline.service;

import com.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import com.thumbtack.airline.dto.response.AdminResponseDTO;
import com.thumbtack.airline.dto.response.ClientResponseDTO;

import java.util.List;

public interface AdminService {
    AdminResponseDTO register(AdminRegistrationRequestDTO request);

    List<ClientResponseDTO> getClients();
}
