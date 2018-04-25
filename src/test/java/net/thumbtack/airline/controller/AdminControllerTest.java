package net.thumbtack.airline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.request.AdminRegistrationRequestDto;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDto;
import net.thumbtack.airline.dto.response.AdminResponseDto;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.AdminService;
import net.thumbtack.airline.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String cookieName = "JAVASESSIONID";
    private final String mediaType = MediaType.APPLICATION_JSON_VALUE;
    private final String uuid = "BEST_UUID";

    @MockBean
    private AdminService adminServiceMock;

    @MockBean
    private UserService userServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void registrationAdminTest() throws Exception {
        AdminRegistrationRequestDto adminRegistrationRequestDto = new AdminRegistrationRequestDto(
                "Левонтий", "Брежнев", "Васильев", "Главный администратор",
                "levontiy1133", "bestpassword"
        );
        AdminResponseDto adminResponseDto = new AdminResponseDto(
                adminRegistrationRequestDto.getFirstName(),
                adminRegistrationRequestDto.getLastName(),
                adminRegistrationRequestDto.getPatronymic(),
                1,
                UserRole.ADMIN,
                adminRegistrationRequestDto.getPosition()
        );
        UserCookieDto userCookieDto = new UserCookieDto(adminResponseDto.getId(), adminResponseDto.getUserType());


        when(adminServiceMock.register(adminRegistrationRequestDto)).thenReturn(adminResponseDto);
        when(userServiceMock.setUserCookie(userCookieDto)).thenReturn(uuid);


        this.mvc.perform(post("/api/admin")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(adminRegistrationRequestDto))
        )
                .andExpect(status().isOk())
                .andExpect(cookie().value(this.cookieName, uuid))
                .andExpect(jsonPath("$.firstName").value(adminResponseDto.getFirstName()))
                .andExpect(jsonPath("$.id").value(adminResponseDto.getId()))
                .andExpect(jsonPath("$.position").value(adminResponseDto.getPosition()));


        verify(adminServiceMock, times(1)).register(adminRegistrationRequestDto);

    }

    @Test
    public void updateAdminTest() throws Exception {
        AdminUpdateRequestDto adminUpdateRequestDto = new AdminUpdateRequestDto(
                "Левонтий", "Брежнев", "Васильев", "Главный администратор",
                "badpassword", "bestpassword"
        );
        adminUpdateRequestDto.setId(1);

        AdminUpdateResponseDto adminUpdateResponseDto = new AdminUpdateResponseDto(
                adminUpdateRequestDto.getFirstName(), adminUpdateRequestDto.getLastName(),
                adminUpdateRequestDto.getPatronymic(), adminUpdateRequestDto.getPosition(),
                UserRole.ADMIN
        );
        when(adminServiceMock.update(adminUpdateRequestDto)).thenReturn(adminUpdateResponseDto);
        when(userServiceMock.authorizeUser(uuid, UserRole.ADMIN)).thenReturn(new UserCookieDto(1, UserRole.ADMIN));


        this.mvc.perform(put("/api/admin")
                .contentType(this.mediaType)
                .content(this.objectMapper.writeValueAsString(adminUpdateRequestDto))
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(adminUpdateRequestDto.getFirstName()))
                .andExpect(jsonPath("$.userType").value(UserRole.ADMIN.toString()));

        when(adminServiceMock.update(adminUpdateRequestDto)).thenThrow(new BaseException(
                ErrorCode.ACCOUNT_NOT_FOUND.getErrorCodeString(),
                ErrorCode.ACCOUNT_NOT_FOUND.getErrorFieldString(),
                ErrorCode.ACCOUNT_NOT_FOUND
        ));
        this.mvc.perform(put("/api/admin")
                .contentType(this.mediaType)
                .content(this.objectMapper.writeValueAsString(adminUpdateRequestDto))
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].errorCode").value(ErrorCode.ACCOUNT_NOT_FOUND.name()))
                .andExpect(jsonPath("$[0].field").value(ErrorCode.ACCOUNT_NOT_FOUND.getErrorFieldString()))
                .andExpect(jsonPath("$[0].message").value(ErrorCode.ACCOUNT_NOT_FOUND.getErrorCodeString()));

        verify(adminServiceMock, times(2)).update(adminUpdateRequestDto);
    }

    @Test
    public void getClientsAdminTest() throws Exception {
        List<ClientResponseDto> clientResponseDtos = new ArrayList<>();
        clientResponseDtos.add(new ClientResponseDto(
                "Левонтий", "Брежнев", "Васильев", 1, UserRole.CLIENT,
                "89627672381", "levontiy@mail.ru"
        ));
        when(adminServiceMock.getClients()).thenReturn(clientResponseDtos);
        when(userServiceMock.authorizeUser(uuid, UserRole.ADMIN)).thenReturn(new UserCookieDto(1, UserRole.ADMIN));


        this.mvc.perform(get("/api/clients")
                .contentType(this.mediaType)
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Левонтий"))
                .andExpect(jsonPath("$[0].userType").value(UserRole.CLIENT.toString()));

        clientResponseDtos.clear();

        when(adminServiceMock.getClients()).thenReturn(clientResponseDtos);
        this.mvc.perform(get("/api/clients")
                .contentType(this.mediaType)
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        verify(adminServiceMock, times(2)).getClients();
    }

    @Test
    public void getPlanesAdminTest() throws Exception {
        List<Plane> planes = new ArrayList<>();
        planes.add(new Plane(
                "Airbus 319", 12, 20, 13, 15
        ));
        when(adminServiceMock.getPlanes()).thenReturn(planes);
        when(userServiceMock.authorizeUser(uuid, UserRole.ADMIN)).thenReturn(new UserCookieDto(1, UserRole.ADMIN));


        this.mvc.perform(get("/api/planes")
                .contentType(this.mediaType)
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Airbus 319"))
                .andExpect(jsonPath("$[0].bussinesRows").value(12));

        planes.clear();
        this.mvc.perform(get("/api/planes")
                .contentType(this.mediaType)
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        verify(adminServiceMock, times(2)).getPlanes();
    }

    @Test
    public void debugClearAdminTest() throws Exception {

        doNothing().when(adminServiceMock).clearDataBase();
        when(userServiceMock.authorizeUser(uuid, UserRole.ADMIN)).thenReturn(new UserCookieDto(1, UserRole.ADMIN));

        this.mvc.perform(delete("/api/debug/clear")
                .contentType(this.mediaType)
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("{}"));

        when(userServiceMock.authorizeUser(uuid, UserRole.ADMIN)).thenThrow(
                new BaseException(ErrorCode.NO_ACCESS.getErrorCodeString(), ErrorCode.NO_ACCESS.getErrorFieldString(),
                ErrorCode.NO_ACCESS)
        );
        this.mvc.perform(delete("/api/debug/clear")
                .contentType(this.mediaType)
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].errorCode").value(ErrorCode.NO_ACCESS.name()))
                .andExpect(jsonPath("$[0].field").value(ErrorCode.NO_ACCESS.getErrorFieldString()))
                .andExpect(jsonPath("$[0].message").value(ErrorCode.NO_ACCESS.getErrorCodeString()));

        verify(adminServiceMock, times(1)).clearDataBase();
    }

}