package net.thumbtack.airline.controller;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dto.BaseLoginDTO;
import net.thumbtack.airline.dto.UserCookieDTO;
import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;
import net.thumbtack.airline.exception.SimpleException;
import net.thumbtack.airline.service.CookieService;
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

    private CookieService cookieService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value(value = "${cookie}")
    private String COOKIE;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCookieService(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @PostMapping("/session")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO,
                                   @CookieValue(value = "${cookie}", defaultValue = "") String uuid, HttpServletResponse response) {
        if(!uuid.isEmpty()) {
            throw new SimpleException(ConstantsSetting.ErrorsConstants.ALREADY_LOGIN.toString(), "", "");
        }
        BaseLoginDTO userResponse = userService.login(loginRequestDTO);
        String cookieValue = cookieService.setUserCookie(new UserCookieDTO(userResponse.getId(), userResponse.getUserType()));
        response.addCookie(new Cookie(COOKIE, cookieValue));
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/session")
    public ResponseEntity<?> logout(@CookieValue(value = "${cookie}", defaultValue = "") String uuid, HttpServletResponse response) {
        if(uuid.isEmpty()) {
            throw new SimpleException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", "");
        }
        cookieService.deleteUserCookie(uuid);
        Cookie cookie = new Cookie(COOKIE, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account")
    public ResponseEntity<?> get(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty()) {
            throw new SimpleException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", "");
        }
        UserDTO userResponse =  userService.get(cookieService.getUserCookie(uuid).getId());
        return ResponseEntity.ok(userResponse);
    }
}
