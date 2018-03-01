package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.service.AdminService;
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

    @Override
    public AdminUpdateResponseDTO update(AdminUpdateRequestDTO request) {
        return null;
    }

    @Override
    public List<Plane> getPlanes() {
        return null;
    }
}
