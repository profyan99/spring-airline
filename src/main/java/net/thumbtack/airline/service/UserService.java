package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.BaseLoginDTO;
import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;

public interface UserService {
    BaseLoginDTO login(LoginRequestDTO loginRequestDTO);

    UserDTO get(int id);
}
