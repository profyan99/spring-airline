package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;

import java.util.List;

public interface AdminService {
    AdminResponseDTO register(AdminRegistrationRequestDTO request);

    List<ClientResponseDTO> getClients();
}
