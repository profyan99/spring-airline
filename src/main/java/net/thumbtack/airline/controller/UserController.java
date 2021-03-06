package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.request.LoginRequestDto;
import net.thumbtack.airline.dto.response.BaseLoginDto;
import net.thumbtack.airline.dto.response.ServerSettingsResponseDto;
import net.thumbtack.airline.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value(value = "${cookie}")
    private String COOKIE;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/session")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto,
                                   @CookieValue(value = "${cookie}", defaultValue = "") String uuid, HttpServletResponse response) {

        BaseLoginDto userResponse = userService.login(loginRequestDto, uuid);
        String cookieValue = userService.setUserCookie(new UserCookieDto(userResponse.getId(), userResponse.getUserType()));
        response.addCookie(new Cookie(COOKIE, cookieValue));
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping(path = "/session")
    public ResponseEntity<?> logout(@CookieValue(value = "${cookie}", defaultValue = "") String uuid, HttpServletResponse response) {
        userService.deleteUserCookie(uuid);
        Cookie cookie = new Cookie(COOKIE, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("{}");
    }

    @GetMapping(path = "/account")
    public ResponseEntity<?> get(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        return ResponseEntity.ok(userService.getUser(userService.authorizeUser(uuid).getId()));
    }

    @GetMapping(path = "/settings")
    public ResponseEntity<?> settings(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        ServerSettingsResponseDto responseDto;
        if (!uuid.isEmpty()) {
            responseDto = userService.getSettings(userService.authorizeUser(uuid).getUserType());
        } else {
            responseDto = userService.getSettings();
        }
        return ResponseEntity.ok(responseDto);
    }
}
