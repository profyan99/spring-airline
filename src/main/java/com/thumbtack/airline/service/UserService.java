package com.thumbtack.airline.service;

import com.thumbtack.airline.dto.request.LoginRequestDTO;
import com.thumbtack.airline.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);

    boolean logout();

    UserResponseDTO information();
}
