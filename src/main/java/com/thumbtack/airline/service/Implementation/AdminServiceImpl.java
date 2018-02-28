package com.thumbtack.airline.service.Implementation;

import com.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import com.thumbtack.airline.dto.response.AdminResponseDTO;
import com.thumbtack.airline.dto.response.ClientResponseDTO;
import com.thumbtack.airline.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    @Override
    public AdminResponseDTO register(AdminRegistrationRequestDTO request) {
        return null;
    }

    @Override
    public List<ClientResponseDTO> getClients() {
        return null;
    }
}
