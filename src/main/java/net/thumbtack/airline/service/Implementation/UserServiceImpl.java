package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;
import net.thumbtack.airline.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public UserDTO get() {
        return null;
    }
}
