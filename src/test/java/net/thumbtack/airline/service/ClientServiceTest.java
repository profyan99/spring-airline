package net.thumbtack.airline.service;

import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.request.ClientRegistrationRequestDto;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.implementation.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ClientServiceTest {
    private ClientServiceImpl clientService;

    @MockBean
    private ClientDao clientDaoMock;

    @MockBean
    private UserDao userDaoMock;

    @Before
    public void setUp() {

        clientService = new ClientServiceImpl();
        clientService.setClientDao(clientDaoMock);
        clientService.setUserDao(userDaoMock);
    }

    @Test
    public void registerClientService_ShouldReturnClientResponseDto() {
        ClientRegistrationRequestDto clientRegistrationRequestDto = new ClientRegistrationRequestDto(
                "Левонтий", "Брежнев", "Васильев", "brezhnev@mail.ru", "8-965-98-10-144",
                "levontiy1133", "bestpassword"
        );

        Client client = new Client(
                clientRegistrationRequestDto.getFirstName(),
                clientRegistrationRequestDto.getLastName(),
                clientRegistrationRequestDto.getPatronymic(),
                clientRegistrationRequestDto.getLogin(),
                clientRegistrationRequestDto.getPassword(),
                clientRegistrationRequestDto.getEmail(),
                clientRegistrationRequestDto.getPhone()
        );

        when(userDaoMock.exists(eq(clientRegistrationRequestDto.getLogin()))).thenReturn(false);
        when(clientDaoMock.register(any(Client.class))).thenReturn(client);

        ClientResponseDto clientResponseDto = clientService.register(clientRegistrationRequestDto);
        assertEquals(clientResponseDto.getPhone(), clientRegistrationRequestDto.getPhone().replace("-", ""));
        assertEquals(clientResponseDto.getFirstName(), clientRegistrationRequestDto.getFirstName());
        assertEquals(clientResponseDto.getUserType(), UserRole.CLIENT);

        verify(clientDaoMock, times(1)).register(any(Client.class));
        verify(userDaoMock, times(1)).exists(eq(clientRegistrationRequestDto.getLogin()));
        verifyNoMoreInteractions(clientDaoMock, userDaoMock);
    }

    @Test(expected = BaseException.class)
    public void registerClientService_AccountAlreadyExists_ShouldThrowException() throws BaseException {
        ClientRegistrationRequestDto clientRegistrationRequestDto = new ClientRegistrationRequestDto(
                "Левонтий", "Брежнев", "Васильев", "brezhnev@mail.ru", "8-965-98-10-144",
                "levontiy1133", "bestpassword"
        );

        Client client = new Client(
                clientRegistrationRequestDto.getFirstName(),
                clientRegistrationRequestDto.getLastName(),
                clientRegistrationRequestDto.getPatronymic(),
                clientRegistrationRequestDto.getLogin(),
                clientRegistrationRequestDto.getPassword(),
                clientRegistrationRequestDto.getEmail(),
                clientRegistrationRequestDto.getPhone()
        );

        when(userDaoMock.exists(eq(clientRegistrationRequestDto.getLogin()))).thenReturn(true);
        when(clientDaoMock.register(any(Client.class))).thenReturn(client);

        clientService.register(clientRegistrationRequestDto);

        verify(clientDaoMock, times(0)).register(any(Client.class));
        verify(userDaoMock, times(1)).exists(eq(clientRegistrationRequestDto.getLogin()));
        verifyNoMoreInteractions(clientDaoMock, userDaoMock);
    }

    @Test
    public void updateClientService_ShouldReturnClientUpdateResponseDto() {
        final int id = 1;
        ClientUpdateRequestDto clientUpdateRequestDto = new ClientUpdateRequestDto(
                "Левонтий", "Брежнев", "Васильев", "brezhnev@mail.ru", "8-965-98-10-144",
                "levontiy1133", "bestpassword"
        );
        clientUpdateRequestDto.setId(id);
        Client client = new Client(
                clientUpdateRequestDto.getFirstName(),
                clientUpdateRequestDto.getLastName(),
                clientUpdateRequestDto.getPatronymic(),
                "SimpleLogin",
                clientUpdateRequestDto.getOldPassword(),
                clientUpdateRequestDto.getEmail(),
                clientUpdateRequestDto.getPhone()
        );

        when(clientDaoMock.findClientById(id)).thenReturn(Optional.of(client));
        doNothing().when(clientDaoMock).updateClient(any(Client.class));

        ClientUpdateResponseDto clientUpdateResponseDto = clientService.update(clientUpdateRequestDto);
        assertEquals(clientUpdateResponseDto.getPhone(), clientUpdateRequestDto.getPhone().replace("-", ""));
        assertEquals(clientUpdateResponseDto.getFirstName(), clientUpdateRequestDto.getFirstName());
        assertEquals(clientUpdateResponseDto.getUserType(), UserRole.CLIENT);

        verify(clientDaoMock, times(1)).findClientById(id);
        verify(clientDaoMock, times(1)).updateClient(any(Client.class));
        verifyNoMoreInteractions(clientDaoMock);
    }

    @Test(expected = BaseException.class)
    public void updateClientService_NotExist_ShouldThrowException() throws BaseException {
        final int id = 1;
        ClientUpdateRequestDto clientUpdateRequestDto = new ClientUpdateRequestDto(
                "Левонтий", "Брежнев", "Васильев", "brezhnev@mail.ru", "8-965-98-10-144",
                "levontiy1133", "bestpassword"
        );

        when(clientDaoMock.findClientById(id)).thenReturn(Optional.empty());
        doNothing().when(clientDaoMock).updateClient(any(Client.class));

        clientService.update(clientUpdateRequestDto);


        verify(clientDaoMock, times(1)).findClientById(id);
        verify(clientDaoMock, times(0)).updateClient(any(Client.class));
        verifyNoMoreInteractions(clientDaoMock);
    }

    @Test(expected = BaseException.class)
    public void updateClientService_InvalidPassword_ShouldThrowException() throws BaseException {
        final int id = 1;
        ClientUpdateRequestDto clientUpdateRequestDto = new ClientUpdateRequestDto(
                "Левонтий", "Брежнев", "Васильев", "brezhnev@mail.ru", "8-965-98-10-144",
                "levontiy1133", "bestpassword"
        );
        clientUpdateRequestDto.setId(id);
        Client client = new Client(
                clientUpdateRequestDto.getFirstName(),
                clientUpdateRequestDto.getLastName(),
                clientUpdateRequestDto.getPatronymic(),
                "SimpleLogin",
                "notequalpass",
                clientUpdateRequestDto.getEmail(),
                clientUpdateRequestDto.getPhone()
        );

        when(clientDaoMock.findClientById(id)).thenReturn(Optional.of(client));
        doNothing().when(clientDaoMock).updateClient(any(Client.class));

        clientService.update(clientUpdateRequestDto);

        verify(clientDaoMock, times(1)).findClientById(id);
        verify(clientDaoMock, times(0)).updateClient(any(Client.class));
        verifyNoMoreInteractions(clientDaoMock);
    }
}
