package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.request.ClientRegistrationRequestDto;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private ClientDao clientDao;

    private UserDao userDao;

    @Autowired
    public void setAdminDAO(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public ClientResponseDto register(ClientRegistrationRequestDto request) {
        ClientResponseDto responseDTO;
        if (userDao.exists(request.getLogin())) {
            throw new BaseException(ErrorCode.ACCOUNT_EXIST_ERROR.getErrorCodeString(),
                    "registration", ErrorCode.ACCOUNT_EXIST_ERROR);
        }
        Client client = new Client(
                request.getFirstName(),
                request.getLastName(),
                request.getPatronymic(),
                request.getLogin(),
                request.getPassword(),
                request.getEmail(),
                request.getPhone().replace("-", "")
        );
        clientDao.register(client);
        responseDTO = new ClientResponseDto(
                client.getFirstName(),
                client.getLastName(),
                client.getPatronymic(),
                client.getId(),
                client.getUserType(),
                client.getPhone(),
                client.getEmail()
        );
        return responseDTO;
    }


    @Override
    public ClientUpdateResponseDto update(ClientUpdateRequestDto request) {
        ClientUpdateResponseDto response;
        Client client = clientDao.findClientById(request.getId());
        if (!client.getPassword().equals(request.getOldPassword())) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD.getErrorCodeString(),
                    "password", ErrorCode.INVALID_PASSWORD);
        }
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setPatronymic(request.getPatronymic());
        client.setPassword(request.getNewPassword());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        clientDao.updateClient(client);
        response = new ClientUpdateResponseDto(
                client.getFirstName(),
                client.getLastName(),
                client.getPatronymic(),
                client.getUserType(),
                client.getPhone(),
                client.getEmail()
        );
        return response;
    }
}
