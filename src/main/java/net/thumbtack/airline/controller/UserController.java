package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.BaseLoginDTO;
import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;
import net.thumbtack.airline.dto.response.ErrorDTO;
import net.thumbtack.airline.service.UserService;
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

    @Value(value = "${cookie}")
    private String COOKIE;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/session")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        ResponseEntity resp;
        BaseLoginDTO userResponse = userService.login(loginRequestDTO);
       if(userResponse != null) {
            resp = ResponseEntity.ok(userResponse);
            Cookie cookie = new Cookie(COOKIE, ""+userResponse.getId());
            response.addCookie(cookie);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_LOGIN", "Don't know", "Error")
            );
        }
        return resp;
    }

    @DeleteMapping("/session")
    public ResponseEntity<?> logout(@CookieValue(value = "${cookie}", defaultValue = "0") int id) {
        ResponseEntity resp;
        if(userService.logout(id)) {
            resp = ResponseEntity.ok().build();
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_LOGOUT", "Don't know", "Error")
            );
        }
        return resp;
    }

    @GetMapping("/account")
    public ResponseEntity<?> get(@CookieValue(value = "${cookie}", defaultValue = "0") int id) {
        ResponseEntity resp;
        UserDTO userResponse =  userService.get(id);
        if(userResponse != null) {
            resp = ResponseEntity.ok(userResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_INFORMATION", "Don't know", "Error")
            );
        }
        return resp;
    }
}
