package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.dto.response.ErrorDTO;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private AdminService adminService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value(value = "${cookie}")
    private String COOKIE;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    public ResponseEntity<?> registration(@RequestBody @Valid AdminRegistrationRequestDTO reg, HttpServletResponse response) {
        ResponseEntity resp;
        AdminResponseDTO adminResponse = adminService.register(reg);
        if (adminResponse != null) {
            resp = ResponseEntity.ok(adminResponse);
            Cookie cookie = new Cookie(COOKIE, ""+adminResponse.getId());
            response.addCookie(cookie);
        } else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_ADMIN_REGISTRATION", "Don't know", "Error")
            );
        }
        return resp;
    }

    @PutMapping("/admin")
    public ResponseEntity<?> update(@RequestBody @Valid AdminUpdateRequestDTO request) {
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
