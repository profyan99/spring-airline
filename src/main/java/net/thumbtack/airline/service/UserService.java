package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;

public interface UserService {
    UserDTO login(LoginRequestDTO loginRequestDTO);

    boolean logout();

    UserDTO get();
}
