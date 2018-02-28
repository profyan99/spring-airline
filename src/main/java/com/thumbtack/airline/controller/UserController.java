package com.thumbtack.airline.controller;

import com.thumbtack.airline.dto.request.LoginRequestDTO;
import com.thumbtack.airline.dto.response.ErrorDTO;
import com.thumbtack.airline.dto.response.UserResponseDTO;
import com.thumbtack.airline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/session")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        ResponseEntity resp;
        UserResponseDTO userResponse = userService.login(loginRequestDTO);
       if(userResponse != null) {
            resp = ResponseEntity.ok(userResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_LOGIN", "Don't know", "Error")
            );
        }
        return resp;
    }

    @DeleteMapping("/session")
    public ResponseEntity<?> logout() {
        ResponseEntity resp;
        if(userService.logout()) {
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
    public ResponseEntity<?> information() {
        ResponseEntity resp;
        UserResponseDTO userResponse =  userService.information();
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
