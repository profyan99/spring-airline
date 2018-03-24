package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dao.ClientDAO;
import net.thumbtack.airline.dao.UserDAO;
import net.thumbtack.airline.dto.request.ClientRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDTO;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ClientDAO clientDAO;

    private UserDAO userDAO;

    @Autowired
    public void setAdminDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ClientResponseDTO register(ClientRegistrationRequestDTO request) {
        ClientResponseDTO responseDTO;
        if (userDAO.exists(request.getLogin())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.ACCOUNT_EXIST_ERROR.toString(),
                    this.getClass().getName(), ErrorCode.ACCOUNT_EXIST_ERROR);
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
        clientDAO.register(client);
        responseDTO = new ClientResponseDTO(
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
    public ClientUpdateResponseDTO update(ClientUpdateRequestDTO request) {
        ClientUpdateResponseDTO response;
        Client client = clientDAO.findClientById(request.getId());
        if (!client.getPassword().equals(request.getOldPassword())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.INVALID_PASSWORD.toString(),
                    this.getClass().getName(), ErrorCode.INVALID_PASSWORD);
        }
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setPatronymic(request.getPatronymic());
        client.setPassword(request.getNewPassword());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());
        clientDAO.updateClient(client);
        response = new ClientUpdateResponseDTO(
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
