package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.request.ClientRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.ClientUpdateRequestDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.dto.response.ClientUpdateResponseDTO;
import net.thumbtack.airline.dto.response.ErrorDTO;
import net.thumbtack.airline.service.ClientService;
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

    @Value(value = "${cookie}")
    private String COOKIE;


    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody @Valid ClientRegistrationRequestDTO reg, HttpServletResponse response) {
        ResponseEntity resp;
        ClientResponseDTO clientResponse = clientService.register(reg);
        if (clientResponse != null) {
            resp = ResponseEntity.ok(clientResponse);
            Cookie cookie = new Cookie(COOKIE, ""+clientResponse.getId());
            response.addCookie(cookie);
        } else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_CLIENT_REGISTRATION", "Don't know", "Error")
            );
        }
        return resp;
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ClientUpdateRequestDTO request) {
        ResponseEntity resp;
        ClientUpdateResponseDTO clientResponse =  clientService.update(request);
        if(clientResponse != null) {
            resp = ResponseEntity.ok(clientResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_UPDATING_CLIENT_PROFILE", "Don't know", "Error")
            );
        }
        return resp;
    }
}
