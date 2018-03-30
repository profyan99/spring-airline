package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.AdminRegistrationRequestDto;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDto;
import net.thumbtack.airline.dto.response.AdminResponseDto;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.model.Plane;

import java.util.List;

public interface AdminService {
    AdminResponseDto register(AdminRegistrationRequestDto request);

    List<ClientResponseDto> getClients();

    AdminUpdateResponseDto update(AdminUpdateRequestDto request);

    List<Plane> getPlanes();

    void clearDataBase();
}
