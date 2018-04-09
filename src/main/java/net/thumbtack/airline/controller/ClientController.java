package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.request.ClientRegistrationRequestDto;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDto;
import net.thumbtack.airline.dto.response.ClientResponseDto;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.ClientService;
import net.thumbtack.airline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private ClientService clientService;

    private UserService userService;

    @Value(value = "${cookie}")
    private String COOKIE;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<?> registration(@RequestBody @Valid ClientRegistrationRequestDto reg, HttpServletResponse response) {
        ClientResponseDto clientResponse = clientService.register(reg);
        String cookieValue = userService.setUserCookie(new UserCookieDto(clientResponse.getId(), clientResponse.getUserType()));
        response.addCookie(new Cookie(COOKIE, cookieValue));
        return ResponseEntity.ok(clientResponse);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ClientUpdateRequestDto request,
                                    @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        request.setId(userService.authorizeUser(uuid, UserRole.CLIENT).getId());
        return ResponseEntity.ok(clientService.update(request));
    }
}
