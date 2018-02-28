package com.thumbtack.airline.controller;

import com.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import com.thumbtack.airline.dto.response.AdminResponseDTO;
import com.thumbtack.airline.dto.response.ClientResponseDTO;
import com.thumbtack.airline.dto.response.ErrorDTO;
import com.thumbtack.airline.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    public ResponseEntity<?> registration(@RequestBody @Valid AdminRegistrationRequestDTO reg, BindingResult result) {
        ResponseEntity resp;
        if(result.hasErrors()) {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO(
                            "ERROR_WITH_ADMIN_REGISTRATION",
                            result.getFieldError().getField(),
                            result.getFieldError().toString()
                    )
            );
        }
        else {
            AdminResponseDTO adminResponse = adminService.register(reg);
            if (adminResponse != null) {
                resp = ResponseEntity.ok(adminResponse);
            } else {
                resp = ResponseEntity.badRequest().body(
                        new ErrorDTO("ERROR_WITH_ADMIN_REGISTRATION", "Don't know", "Error")
                );
            }
        }
        return resp;
    }

    @PutMapping("/admin")
    public ResponseEntity<?> update() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clients")
    public ResponseEntity<?> clients() {
        ResponseEntity resp;
        List<ClientResponseDTO > adminResponse =  adminService.getClients();
        if(!adminResponse.isEmpty()) {
            resp = ResponseEntity.ok(adminResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_GETTING_CLIENTS", "Don't know", "Error")
            );
        }
        return resp;
    }
}
