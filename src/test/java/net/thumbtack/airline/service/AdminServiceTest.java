package net.thumbtack.airline.service;

import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.request.AdminRegistrationRequestDto;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDto;
import net.thumbtack.airline.dto.response.AdminResponseDto;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.model.Admin;
import net.thumbtack.airline.model.Client;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.Implementation.AdminServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AdminServiceTest {

    private AdminServiceImpl adminService;

    @MockBean
    private AdminDao adminDaoMock;

    @MockBean
    private UserDao userDaoMock;

    @Before
    public void setUp() {

        adminService = new AdminServiceImpl();
        adminService.setAdminDao(adminDaoMock);
        adminService.setUserDao(userDaoMock);
    }

    @Test
    public void register_ShouldReturnAdminEntry() {
        AdminRegistrationRequestDto adminRegistrationRequestDto = new AdminRegistrationRequestDto(
                "Левонтий", "Брежнев", "Васильев", "Главный администратор",
                "levontiy1133", "bestpassword"
        );

        Admin admin = new Admin(
                adminRegistrationRequestDto.getFirstName(),
                adminRegistrationRequestDto.getLastName(),
                adminRegistrationRequestDto.getPatronymic(),
                adminRegistrationRequestDto.getLogin(),
                adminRegistrationRequestDto.getPassword(),
                adminRegistrationRequestDto.getPosition()
        );

        when(userDaoMock.exists(eq(adminRegistrationRequestDto.getLogin()))).thenReturn(false);
        when(adminDaoMock.register(any(Admin.class))).thenReturn(admin);

        AdminResponseDto adminResponseDto = adminService.register(adminRegistrationRequestDto);
        assertEquals(adminResponseDto.getPosition(), adminRegistrationRequestDto.getPosition());
        assertEquals(adminResponseDto.getFirstName(), adminRegistrationRequestDto.getFirstName());
        assertEquals(adminResponseDto.getUserType(), UserRole.ADMIN);

        verify(adminDaoMock, times(1)).register(any(Admin.class));
        verify(userDaoMock, times(1)).exists(eq(adminRegistrationRequestDto.getLogin()));
        verifyNoMoreInteractions(adminDaoMock, userDaoMock);
    }

    @Test(expected = BaseException.class)
    public void register_AccountAlreadyExists_ShouldThrowException() throws BaseException {
        AdminRegistrationRequestDto adminRegistrationRequestDto = new AdminRegistrationRequestDto(
                "Левонтий", "Брежнев", "Васильев", "Главный администратор",
                "levontiy1133", "bestpassword"
        );

        Admin admin = new Admin(
                adminRegistrationRequestDto.getFirstName(),
                adminRegistrationRequestDto.getLastName(),
                adminRegistrationRequestDto.getPatronymic(),
                adminRegistrationRequestDto.getLogin(),
                adminRegistrationRequestDto.getPassword(),
                adminRegistrationRequestDto.getPosition()
        );

        when(userDaoMock.exists(eq(adminRegistrationRequestDto.getLogin()))).thenReturn(true);
        when(adminDaoMock.register(any(Admin.class))).thenReturn(admin);

        adminService.register(adminRegistrationRequestDto);

        verify(adminDaoMock, times(0)).register(any(Admin.class));
        verify(userDaoMock, times(1)).exists(eq(adminRegistrationRequestDto.getLogin()));
        verifyNoMoreInteractions(adminDaoMock, userDaoMock);
    }

    @Test
    public void getClients_ShouldReturnListOfClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Петр", "Васюта", "Васильевич",
                "vasyyta", "passbest", "nsadj@mail.com", "80989798703"));
        clients.add(new Client("Настя", "Васюта", "Петровна",
                "vasyytaNastya", "passbest", "sdasdf@mail.com", "9823749234"));
        when(adminDaoMock.getClients()).thenReturn(clients);

        List<ClientResponseDto> actualClients = adminService.getClients();
        assertThat(actualClients.size(), is(clients.size()));
        assertEquals(actualClients.get(0).getPhone(), "80989798703");
        assertEquals(actualClients.get(1).getFirstName(), "Настя");

        verify(adminDaoMock, times(1)).getClients();
        verifyNoMoreInteractions(adminDaoMock);
    }

    @Test
    public void getClients_NoClients_ShouldReturnEmptyListOfClients() {
        List<Client> clients = new ArrayList<>();
        when(adminDaoMock.getClients()).thenReturn(clients);

        List<ClientResponseDto> actualClients = adminService.getClients();
        assertThat(actualClients.size(), is(clients.size()));

        verify(adminDaoMock, times(1)).getClients();
        verifyNoMoreInteractions(adminDaoMock);
    }

    @Test
    public void update_ShouldUpdateAdmin() {
        final int id = 1;
        AdminUpdateRequestDto adminUpdateRequestDto = new AdminUpdateRequestDto(
                "Левонтий", "Брежнев", "Васильев", "Главный администратор",
                "badpassword", "bestpassword"
        );
        adminUpdateRequestDto.setId(id);
        Admin admin = new Admin(
                adminUpdateRequestDto.getFirstName(),
                adminUpdateRequestDto.getLastName(),
                adminUpdateRequestDto.getPatronymic(),
                "SimpleLogin",
                adminUpdateRequestDto.getOldPassword(),
                adminUpdateRequestDto.getPosition()
        );

        when(adminDaoMock.findAdminById(id)).thenReturn(Optional.of(admin));
        doNothing().when(adminDaoMock).updateAdmin(any(Admin.class));

        AdminUpdateResponseDto adminUpdateResponseDto = adminService.update(adminUpdateRequestDto);
        assertEquals(adminUpdateResponseDto.getPosition(), adminUpdateRequestDto.getPosition());
        assertEquals(adminUpdateResponseDto.getFirstName(), adminUpdateRequestDto.getFirstName());
        assertEquals(adminUpdateResponseDto.getUserType(), UserRole.ADMIN);

        verify(adminDaoMock, times(1)).findAdminById(id);
        verify(adminDaoMock, times(1)).updateAdmin(any(Admin.class));
        verifyNoMoreInteractions(adminDaoMock);
    }

    @Test(expected = BaseException.class)
    public void update_NotExist_ShouldThrowException() throws BaseException {
        final int id = 1;
        AdminUpdateRequestDto adminUpdateRequestDto = new AdminUpdateRequestDto(
                "Левонтий", "Брежнев", "Васильев", "Главный администратор",
                "badpassword", "bestpassword"
        );

        when(adminDaoMock.findAdminById(id)).thenReturn(Optional.empty());
        doNothing().when(adminDaoMock).updateAdmin(any(Admin.class));

        adminService.update(adminUpdateRequestDto);


        verify(adminDaoMock, times(1)).findAdminById(id);
        verify(adminDaoMock, times(0)).updateAdmin(any(Admin.class));
        verifyNoMoreInteractions(adminDaoMock);
    }

    @Test(expected = BaseException.class)
    public void update_InvalidPassword_ShouldThrowException() throws BaseException {
        final int id = 1;
        AdminUpdateRequestDto adminUpdateRequestDto = new AdminUpdateRequestDto(
                "Левонтий", "Брежнев", "Васильев", "Главный администратор",
                "badpassword", "bestpassword"
        );
        adminUpdateRequestDto.setId(id);
        Admin admin = new Admin(
                adminUpdateRequestDto.getFirstName(),
                adminUpdateRequestDto.getLastName(),
                adminUpdateRequestDto.getPatronymic(),
                "SimpleLogin",
                "notequalpass",
                adminUpdateRequestDto.getPosition()
        );

        when(adminDaoMock.findAdminById(id)).thenReturn(Optional.of(admin));
        doNothing().when(adminDaoMock).updateAdmin(any(Admin.class));

        adminService.update(adminUpdateRequestDto);

        verify(adminDaoMock, times(1)).findAdminById(id);
        verify(adminDaoMock, times(0)).updateAdmin(any(Admin.class));
        verifyNoMoreInteractions(adminDaoMock);
    }
}
