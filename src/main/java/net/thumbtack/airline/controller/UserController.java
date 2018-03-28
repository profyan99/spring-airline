package net.thumbtack.airline.controller;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dto.UserCookieDTO;
import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;
import net.thumbtack.airline.dto.response.BaseLoginDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
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
        // REVU move to Cookie service
    	// e.g. cookieService.checkExists(uuid);
    	if(!uuid.isEmpty() && cookieService.exists(uuid)) {
    		// REVU why do you have ErrorsConstants.ALREADY_LOGIN and ErrorCode.ALREADY_LOGIN at the same time ?
    		// I think ErrorCode.ALREADY_LOGIN is enough 
    		// add constructor by String to ErrorCode and String getCodeString()
    		// let ErrorCode know it's strings
            throw new BaseException(ConstantsSetting.ErrorsConstants.ALREADY_LOGIN.toString(), "",  ErrorCode.ALREADY_LOGIN);
        }
        BaseLoginDto userResponse = userService.login(loginRequestDTO);
        String cookieValue = cookieService.setUserCookie(new UserCookieDTO(userResponse.getId(), userResponse.getUserType()));
        response.addCookie(new Cookie(COOKIE, cookieValue));
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/session")
    public ResponseEntity<?> logout(@CookieValue(value = "${cookie}", defaultValue = "") String uuid, HttpServletResponse response) {
        if(uuid.isEmpty()) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", ErrorCode.UNAUTHORISED_ERROR);
        }
        cookieService.deleteUserCookie(uuid);
        Cookie cookie = new Cookie(COOKIE, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/account")
    public ResponseEntity<?> get(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty()) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "",  ErrorCode.UNAUTHORISED_ERROR);
        }
        UserDTO userResponse =  userService.get(cookieService.getUserCookie(uuid).getId());
        return ResponseEntity.ok(userResponse);
    }
}
