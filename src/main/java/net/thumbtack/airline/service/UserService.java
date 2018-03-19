package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;
import net.thumbtack.airline.dto.response.BaseLoginDto;

public interface UserService {
    BaseLoginDto login(LoginRequestDTO loginRequestDTO);

    UserDTO get(int id);
}
