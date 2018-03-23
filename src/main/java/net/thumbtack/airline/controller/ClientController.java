package net.thumbtack.airline.controller;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dto.UserCookieDTO;
import net.thumbtack.airline.dto.request.ClientRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDTO;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.ClientService;
import net.thumbtack.airline.service.CookieService;
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

    private CookieService cookieService;

    @Value(value = "${cookie}")
    private String COOKIE;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setCookieService(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody @Valid ClientRegistrationRequestDTO reg, HttpServletResponse response) {
        ClientResponseDTO clientResponse = clientService.register(reg);
        String cookieValue = cookieService.setUserCookie(new UserCookieDTO(clientResponse.getId(), clientResponse.getUserType()));
        response.addCookie(new Cookie(COOKIE, cookieValue));
        return ResponseEntity.ok(clientResponse);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ClientUpdateRequestDTO request,
                                    @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty() || !cookieService.getUserCookie(uuid).getUserType().equals(UserRole.CLIENT_ROLE.toString())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "",  ErrorCode.UNAUTHORISED_ERROR);
        }
        ClientUpdateResponseDTO clientResponse =  clientService.update(request);
        return ResponseEntity.ok(clientResponse);
    }
}
