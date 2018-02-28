package com.thumbtack.airline.service.Implementation;

import com.thumbtack.airline.dto.request.LoginRequestDTO;
import com.thumbtack.airline.dto.response.UserResponseDTO;
import com.thumbtack.airline.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public UserResponseDTO information() {
        return null;
    }
}
