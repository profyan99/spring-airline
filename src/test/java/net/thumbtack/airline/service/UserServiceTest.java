package net.thumbtack.airline.service;

import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.dao.ClientDao;
import net.thumbtack.airline.dao.CookieDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.request.LoginRequestDto;
import net.thumbtack.airline.dto.response.AdminResponseDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.BaseUser;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.Implementation.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    private UserServiceImpl userService;

    private final String uuid = "BEST_UUID";

    private final int id  = 1;

    private BaseUser baseUser;

    @MockBean
    private ClientDao clientDaoMock;

    @MockBean
    private UserDao userDaoMock;

    @MockBean
    private AdminDao adminDaoMock;

    @MockBean
    private CookieDao cookieDaoMock;

    @Before
    public void setUp() {
        userService = new UserServiceImpl();
        userService.setClientDao(clientDaoMock);
        userService.setUserDao(userDaoMock);
        userService.setAdminDao(adminDaoMock);
        userService.setCookieDao(cookieDaoMock);

        baseUser = new BaseUser(id, "Левонтий", "Брежнев", "Васильев",
                UserRole.CLIENT, "levontiy1133", "bestpassword");
    }

    @Test
    public void loginUserService_ShouldReturnClientResponseDto() {
        baseUser.setUserType(UserRole.CLIENT);

        Client client = new Client(
                baseUser.getFirstName(), baseUser.getLastName(), baseUser.getPatronymic(), baseUser.getLogin(),
                baseUser.getPassword(), "brezhnev@mail.ru", "89659810144"
        );

        LoginRequestDto loginRequestDto = new LoginRequestDto(
                baseUser.getLogin(), baseUser.getPassword()
        );

        when(cookieDaoMock.exists(uuid)).thenReturn(false);
        when(userDaoMock.login(anyString())).thenReturn(Optional.of(baseUser));
        when(clientDaoMock.getClient(anyInt())).thenReturn(Optional.of(client));
        when(adminDaoMock.getAdmin(anyInt())).thenReturn(Optional.of(new Admin()));

        ClientResponseDto clientResponseDto = (ClientResponseDto) userService.login(loginRequestDto, uuid);
        assertEquals(clientResponseDto.getPhone(), client.getPhone());
        assertEquals(clientResponseDto.getUserType(), baseUser.getUserType());
        assertEquals(clientResponseDto.getId(), baseUser.getId());

        verify(cookieDaoMock, times(1)).exists(uuid);
        verify(userDaoMock, times(1)).login(anyString());
        verify(clientDaoMock, times(1)).getClient(anyInt());
        verify(adminDaoMock, times(0)).getAdmin(anyInt());
        verifyNoMoreInteractions(clientDaoMock, userDaoMock, cookieDaoMock, adminDaoMock);
    }

    @Test
    public void loginUserService_ShouldReturnAdminResponseDto() {
        baseUser.setUserType(UserRole.ADMIN);

        Admin admin = new Admin(baseUser.getFirstName(), baseUser.getLastName(), baseUser.getPatronymic(),
                baseUser.getLogin(), baseUser.getPassword(), "Главный администратор");

        LoginRequestDto loginRequestDto = new LoginRequestDto(
                baseUser.getLogin(), baseUser.getPassword()
        );

        when(cookieDaoMock.exists(uuid)).thenReturn(false);
        when(userDaoMock.login(anyString())).thenReturn(Optional.of(baseUser));
        when(clientDaoMock.getClient(anyInt())).thenReturn(Optional.of(new Client()));
        when(adminDaoMock.getAdmin(anyInt())).thenReturn(Optional.of(admin));

        AdminResponseDto adminResponseDto = (AdminResponseDto) userService.login(loginRequestDto, uuid);
        assertEquals(adminResponseDto.getPosition(), admin.getPosition());
        assertEquals(adminResponseDto.getUserType(), baseUser.getUserType());
        assertEquals(adminResponseDto.getId(), baseUser.getId());

        verify(cookieDaoMock, times(1)).exists(uuid);
        verify(userDaoMock, times(1)).login(anyString());
        verify(clientDaoMock, times(0)).getClient(anyInt());
        verify(adminDaoMock, times(1)).getAdmin(anyInt());
        verifyNoMoreInteractions(clientDaoMock, userDaoMock, cookieDaoMock, adminDaoMock);
    }

    @Test(expected = BaseException.class)
    public void loginUserService_AlreadyLogin_ShouldThrowException() {
        baseUser.setUserType(UserRole.CLIENT);

        LoginRequestDto loginRequestDto = new LoginRequestDto(
                baseUser.getLogin(), baseUser.getPassword()
        );

        when(cookieDaoMock.exists(uuid)).thenReturn(true);
        when(userDaoMock.login(anyString())).thenReturn(Optional.of(new BaseUser()));
        when(clientDaoMock.getClient(anyInt())).thenReturn(Optional.of(new Client()));
        when(adminDaoMock.getAdmin(anyInt())).thenReturn(Optional.of(new Admin()));

        userService.login(loginRequestDto, uuid);

        verify(cookieDaoMock, times(1)).exists(uuid);
        verify(userDaoMock, times(0)).login(anyString());
        verify(clientDaoMock, times(0)).getClient(anyInt());
        verify(adminDaoMock, times(0)).getAdmin(anyInt());
        verifyNoMoreInteractions(clientDaoMock, userDaoMock, cookieDaoMock, adminDaoMock);
    }

    @Test(expected = BaseException.class)
    public void loginUserService_AccountNotFound_ShouldThrowException() {
        LoginRequestDto loginRequestDto = new LoginRequestDto(
                baseUser.getLogin(), baseUser.getPassword()
        );

        when(cookieDaoMock.exists(uuid)).thenReturn(false);
        when(userDaoMock.login(anyString())).thenReturn(Optional.empty());
        when(clientDaoMock.getClient(anyInt())).thenReturn(Optional.of(new Client()));
        when(adminDaoMock.getAdmin(anyInt())).thenReturn(Optional.of(new Admin()));

        userService.login(loginRequestDto, uuid);

        verify(cookieDaoMock, times(1)).exists(uuid);
        verify(userDaoMock, times(1)).login(anyString());
        verify(clientDaoMock, times(0)).getClient(anyInt());
        verify(adminDaoMock, times(0)).getAdmin(anyInt());
        verifyNoMoreInteractions(clientDaoMock, userDaoMock, cookieDaoMock, adminDaoMock);
    }

    @Test(expected = BaseException.class)
    public void loginUserService_InvalidPassword_ShouldThrowException() {
        LoginRequestDto loginRequestDto = new LoginRequestDto(
                baseUser.getLogin(), "notequalpassword"
        );

        when(cookieDaoMock.exists(uuid)).thenReturn(false);
        when(userDaoMock.login(anyString())).thenReturn(Optional.of(baseUser));
        when(clientDaoMock.getClient(anyInt())).thenReturn(Optional.of(new Client()));
        when(adminDaoMock.getAdmin(anyInt())).thenReturn(Optional.of(new Admin()));

        userService.login(loginRequestDto, uuid);

        verify(cookieDaoMock, times(1)).exists(uuid);
        verify(userDaoMock, times(1)).login(anyString());
        verify(clientDaoMock, times(0)).getClient(anyInt());
        verify(adminDaoMock, times(0)).getAdmin(anyInt());
        verifyNoMoreInteractions(clientDaoMock, userDaoMock, cookieDaoMock, adminDaoMock);
    }

    @Test
    public void getUserService_ShouldReturnClientResponseDto() {
        baseUser.setUserType(UserRole.CLIENT);

        Client client = new Client(
                baseUser.getFirstName(), baseUser.getLastName(), baseUser.getPatronymic(), baseUser.getLogin(),
                baseUser.getPassword(), "brezhnev@mail.ru", "89659810144"
        );

        when(userDaoMock.get(anyInt())).thenReturn(Optional.of(baseUser));
        when(clientDaoMock.getClient(anyInt())).thenReturn(Optional.of(client));
        when(adminDaoMock.getAdmin(anyInt())).thenReturn(Optional.of(new Admin()));

        ClientResponseDto clientResponseDto = (ClientResponseDto) userService.get(id);
        assertEquals(clientResponseDto.getPhone(), client.getPhone());
        assertEquals(clientResponseDto.getUserType(), baseUser.getUserType());
        assertEquals(clientResponseDto.getId(), baseUser.getId());

        verify(userDaoMock, times(1)).get(anyInt());
        verify(clientDaoMock, times(1)).getClient(anyInt());
        verify(adminDaoMock, times(0)).getAdmin(anyInt());
        verifyNoMoreInteractions(clientDaoMock, userDaoMock, adminDaoMock);
    }

}
