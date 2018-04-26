package net.thumbtack.airline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.request.ClientRegistrationRequestDto;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.ClientService;
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

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    @Autowired
    private MockMvc mvc;

    private final String cookieName = "JAVASESSIONID";
    private final String mediaType = MediaType.APPLICATION_JSON_VALUE;
    private final String uuid = "BEST_UUID";

    @MockBean
    private ClientService clientServiceMock;

    @MockBean
    private UserService userServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void registrationTestClient() throws Exception {
        ClientRegistrationRequestDto clientRegistrationRequestDto = new ClientRegistrationRequestDto(
                "Левонтий", "Брежнев", "Васильев", "brezhnev@mail.ru", "8-965-98-10-144",
                "levontiy1133", "bestpassword"
        );
        ClientResponseDto clientResponseDto = new ClientResponseDto(
                clientRegistrationRequestDto.getFirstName(),
                clientRegistrationRequestDto.getLastName(),
                clientRegistrationRequestDto.getPatronymic(),
                1,
                UserRole.CLIENT,
                clientRegistrationRequestDto.getPhone(),
                clientRegistrationRequestDto.getEmail()
        );
        when(clientServiceMock.register(any(ClientRegistrationRequestDto.class))).thenReturn(clientResponseDto);
        when(userServiceMock.setUserCookie(any(UserCookieDto.class))).thenReturn(uuid);


        this.mvc.perform(post("/api/client")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(clientRegistrationRequestDto))
        )
                .andExpect(status().isOk())
                .andExpect(cookie().value(this.cookieName, uuid))
                .andExpect(jsonPath("$.firstName").value(clientResponseDto.getFirstName()))
                .andExpect(jsonPath("$.id").value(clientResponseDto.getId()))
                .andExpect(jsonPath("$.phone").value(clientResponseDto.getPhone()))
                .andExpect(jsonPath("$.userType").value(UserRole.CLIENT.toString()));

        clientRegistrationRequestDto.setLogin("Not_valid_Login");
        clientRegistrationRequestDto.setPassword("min");
        this.mvc.perform(post("/api/client")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(clientRegistrationRequestDto))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.errors[*].field", containsInAnyOrder("login", "password")));

        verify(clientServiceMock, times(1)).register(any(ClientRegistrationRequestDto.class));
    }

    @Test
    public void updateAdminTest() throws Exception {
        ClientUpdateRequestDto clientUpdateRequestDto = new ClientUpdateRequestDto(
                "Левонтий", "Брежнев", "Васильев", "brezhnev@mail.ru", "8-965-98-10-144",
                "levontiy1133", "bestpassword"
        );
        clientUpdateRequestDto.setId(1);

        ClientUpdateResponseDto clientUpdateResponseDto  = new ClientUpdateResponseDto(
                clientUpdateRequestDto.getFirstName(),
                clientUpdateRequestDto.getLastName(),
                clientUpdateRequestDto.getPatronymic(),
                UserRole.CLIENT,
                clientUpdateRequestDto.getPhone(),
                clientUpdateRequestDto.getEmail()
        );
        when(clientServiceMock.update(any(ClientUpdateRequestDto.class))).thenReturn(clientUpdateResponseDto);
        when(userServiceMock.authorizeUser(uuid, UserRole.CLIENT)).thenReturn(new UserCookieDto(1, UserRole.CLIENT));


        this.mvc.perform(put("/api/client")
                .contentType(this.mediaType)
                .content(this.objectMapper.writeValueAsString(clientUpdateRequestDto))
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(clientUpdateRequestDto.getFirstName()))
                .andExpect(jsonPath("$.userType").value(UserRole.CLIENT.toString()));

        when(clientServiceMock.update(any(ClientUpdateRequestDto.class))).thenThrow(new BaseException(
                ErrorCode.ACCOUNT_NOT_FOUND.getErrorCodeString(),
                ErrorCode.ACCOUNT_NOT_FOUND.getErrorFieldString(),
                ErrorCode.ACCOUNT_NOT_FOUND
        ));
        this.mvc.perform(put("/api/client")
                .contentType(this.mediaType)
                .content(this.objectMapper.writeValueAsString(clientUpdateRequestDto))
                .cookie(new Cookie(this.cookieName, this.uuid))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].errorCode").value(ErrorCode.ACCOUNT_NOT_FOUND.name()))
                .andExpect(jsonPath("$.errors[0].field").value(ErrorCode.ACCOUNT_NOT_FOUND.getErrorFieldString()))
                .andExpect(jsonPath("$.errors[0].message").value(ErrorCode.ACCOUNT_NOT_FOUND.getErrorCodeString()));

        verify(clientServiceMock, times(2)).update(any(ClientUpdateRequestDto.class));
    }
}
