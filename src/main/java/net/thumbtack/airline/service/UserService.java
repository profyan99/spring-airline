package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.LoginRequestDTO;
import net.thumbtack.airline.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);

    boolean logout();

    UserResponseDTO information();
}
