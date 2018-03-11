package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.BaseLoginDTO;
import net.thumbtack.airline.dto.UserDTO;
import net.thumbtack.airline.dto.request.LoginRequestDTO;
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
        BaseLoginDTO userResponse = userService.login(loginRequestDTO);
        Cookie cookie = new Cookie(COOKIE, ""+userResponse.getId());
        response.addCookie(cookie);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/session")
    public ResponseEntity<?> logout(@CookieValue(value = "${cookie}", defaultValue = "0") int id) {
        userService.logout(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account")
    public ResponseEntity<?> get(@CookieValue(value = "${cookie}", defaultValue = "0") int id) {
        UserDTO userResponse =  userService.get(id);
        return ResponseEntity.ok(userResponse);
    }
}
