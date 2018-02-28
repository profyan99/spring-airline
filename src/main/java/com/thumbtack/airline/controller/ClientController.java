package com.thumbtack.airline.controller;

import com.thumbtack.airline.dto.request.ClientRegistrationRequestDTO;
import com.thumbtack.airline.dto.response.ClientResponseDTO;
import com.thumbtack.airline.dto.response.ErrorDTO;
import com.thumbtack.airline.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/client", consumes = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody @Valid ClientRegistrationRequestDTO reg, BindingResult result) {
        ResponseEntity resp;
        if(result.hasErrors()) {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO(
                            "ERROR_WITH_CLIENT_REGISTRATION",
                            result.getFieldError().getField(),
                            result.getFieldError().toString()
                    )
            );
        }
        else {
            ClientResponseDTO clientResponse = clientService.register(reg);
            if (clientResponse != null) {
                resp = ResponseEntity.ok(clientResponse);
            } else {
                resp = ResponseEntity.badRequest().body(
                        new ErrorDTO("ERROR_WITH_CLIENT_REGISTRATION", "Don't know", "Error")
                );
            }
        }
        return resp;
    }
}
