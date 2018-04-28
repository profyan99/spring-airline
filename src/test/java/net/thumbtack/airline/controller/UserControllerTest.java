package net.thumbtack.airline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.request.LoginRequestDto;
import net.thumbtack.airline.dto.response.BaseLoginDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.UserRole;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    private final String cookieName = "JAVASESSIONID";
    private final String mediaType = MediaType.APPLICATION_JSON_VALUE;
    private final String uuid = "BEST_UUID";

    @MockBean
    private UserService userServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void login_ShouldReturnLoginDto() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto(
                "dmitriy1337", "bestPassword"
        );
        BaseLoginDto baseLoginDto = new ClientResponseDto(
                "Дмитрий", "Приходько", "Васильевич", 1, UserRole.CLIENT,
                "82947294481", "dmitriy@mail.ru"
        );
        when(userServiceMock.login(any(LoginRequestDto.class), eq(""))).thenReturn(baseLoginDto);
        when(userServiceMock.setUserCookie(any(UserCookieDto.class))).thenReturn(uuid);

        this.mvc.perform(post("/api/session")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(loginRequestDto))
        )
                .andExpect(status().isOk())
                .andExpect(cookie().value(this.cookieName, uuid))
                .andExpect(jsonPath("$.firstName").value(baseLoginDto.getFirstName()))
                .andExpect(jsonPath("$.id").value(baseLoginDto.getId()))
                .andExpect(jsonPath("$.patronymic").value(baseLoginDto.getPatronymic()))
                .andExpect(jsonPath("$.userType").value(UserRole.CLIENT.toString()))
                .andExpect(jsonPath("$.email").value("dmitriy@mail.ru"));

        verify(userServiceMock, times(1)).login(any(LoginRequestDto.class), eq(""));
        verify(userServiceMock, times(1)).setUserCookie(any(UserCookieDto.class));
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void login_AccountNotFound_ShouldThrowException() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto(
                "dmitriy1337", "bestPassword"
        );
        when(userServiceMock.setUserCookie(any(UserCookieDto.class))).thenReturn(uuid);
        when(userServiceMock.login(any(LoginRequestDto.class), eq(""))).thenThrow(
                new BaseException(
                        ErrorCode.ACCOUNT_NOT_FOUND.getErrorCodeString(),
                        ErrorCode.ACCOUNT_NOT_FOUND.getErrorFieldString(),
                        ErrorCode.ACCOUNT_NOT_FOUND
                )
        );

        this.mvc.perform(post("/api/session")
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(loginRequestDto))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[0].errorCode").value(ErrorCode.ACCOUNT_NOT_FOUND.name()))
                .andExpect(jsonPath("$.errors[0].field").value(ErrorCode.ACCOUNT_NOT_FOUND.getErrorFieldString()))
                .andExpect(jsonPath("$.errors[0].message").value(ErrorCode.ACCOUNT_NOT_FOUND.getErrorCodeString()));

        verify(userServiceMock, times(1)).login(any(LoginRequestDto.class), eq(""));
        verify(userServiceMock, times(0)).setUserCookie(any(UserCookieDto.class));
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void logout_ShouldDeleteCookie() throws Exception {
        doNothing().when(userServiceMock).deleteUserCookie(eq(uuid));

        this.mvc.perform(delete("/api/session")
                .contentType(mediaType)
                .cookie(new Cookie(cookieName, uuid))
        )
                .andExpect(status().isOk())
                .andExpect(cookie().value(cookieName, nullValue()))
                .andExpect(content().string("{}"));

        verify(userServiceMock, times(1)).deleteUserCookie(eq(uuid));
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void logout_NoCookie_ShouldThrowException() throws Exception {

        doThrow(new BaseException(
                ErrorCode.UNAUTHORISED_ERROR.getErrorCodeString(),
                ErrorCode.UNAUTHORISED_ERROR.getErrorFieldString(),
                ErrorCode.UNAUTHORISED_ERROR
        )).when(userServiceMock).deleteUserCookie(anyString());

        this.mvc.perform(delete("/api/session")
                .contentType(mediaType)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[0].errorCode").value(ErrorCode.UNAUTHORISED_ERROR.name()))
                .andExpect(jsonPath("$.errors[0].field").value(ErrorCode.UNAUTHORISED_ERROR.getErrorFieldString()))
                .andExpect(jsonPath("$.errors[0].message").value(ErrorCode.UNAUTHORISED_ERROR.getErrorCodeString()));

        verify(userServiceMock, times(1)).deleteUserCookie(anyString());
        verifyNoMoreInteractions(userServiceMock);
    }
}
