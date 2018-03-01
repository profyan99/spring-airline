package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.model.Plane;

import java.util.List;

public interface AdminService {
    AdminResponseDTO register(AdminRegistrationRequestDTO request);

    List<ClientResponseDTO> getClients();

    AdminUpdateResponseDTO update(AdminUpdateRequestDTO request);

    List<Plane> getPlanes();
}
