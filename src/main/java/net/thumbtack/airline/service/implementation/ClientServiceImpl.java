package net.thumbtack.airline.service.implementation;

import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.request.ClientRegistrationRequestDto;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static net.thumbtack.airline.exception.ErrorCode.*;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private ClientDao clientDao;

    private UserDao userDao;

    @Autowired
    public void setClientDao(ClientDao clientDao) {
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
            throw new BaseException(ACCOUNT_EXIST_ERROR);
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
        Client client = clientDao.findClientById(request.getId()).orElseThrow(() ->
                new BaseException(ACCOUNT_NOT_FOUND)
        );
        if (!client.getPassword().equals(request.getOldPassword())) {
            throw new BaseException(INVALID_PASSWORD);
        }
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setPatronymic(request.getPatronymic());
        client.setPassword(request.getNewPassword());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone().replace("-", ""));
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
