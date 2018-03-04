package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dto.BaseLoginDTO;
import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;
import net.thumbtack.airline.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public BaseLoginDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Override
    public boolean logout(int id) {
        return false;
    }

    @Override
    public UserDTO get(int id) {
        return null;
    }
}
