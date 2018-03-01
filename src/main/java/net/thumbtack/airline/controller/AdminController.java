package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.dto.response.ErrorDTO;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.service.AdminService;
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
    public ResponseEntity<?> update(@RequestBody AdminUpdateRequestDTO request) {
        ResponseEntity resp;
        AdminUpdateResponseDTO adminResponse =  adminService.update(request);
        if(adminResponse != null) {
            resp = ResponseEntity.ok(adminResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_UPDATING_ADMIN_PROFILE", "Don't know", "Error")
            );
        }
        return resp;
    }

    @GetMapping("/clients")
    public ResponseEntity<?> clients() {
        ResponseEntity resp;
        List<ClientResponseDTO> adminResponse =  adminService.getClients();
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

    @GetMapping("/planes")
    public ResponseEntity<?> planes() {
        ResponseEntity resp;
        List<Plane> adminResponse =  adminService.getPlanes();
        if(!adminResponse.isEmpty()) {
            resp = ResponseEntity.ok(adminResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("NO_PLANES_IN_DATABASE", "Empty list", "Warning")
            );
        }
        return resp;
    }
}
