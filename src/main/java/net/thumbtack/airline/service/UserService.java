package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;
import net.thumbtack.airline.dto.response.BaseLoginDto;
import net.thumbtack.airline.model.Country;

import java.util.List;

public interface UserService {
    BaseLoginDto login(LoginRequestDTO loginRequestDTO);

    UserDTO get(int id);

    List<Country> getCountries();
}
