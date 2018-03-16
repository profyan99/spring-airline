package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
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
        AdminResponseDTO adminResponse = adminService.register(reg);
        ResponseEntity<?> resp = ResponseEntity.ok(adminResponse);
        Cookie cookie = new Cookie(COOKIE, ""+adminResponse.getId());
        response.addCookie(cookie);
        return resp;
    }

    @PutMapping("/admin")
    public ResponseEntity<?> update(@RequestBody @Valid AdminUpdateRequestDTO request) {
        AdminUpdateResponseDTO adminResponse =  adminService.update(request);
        return ResponseEntity.ok(adminResponse);
    }

    @GetMapping("/clients")
    public ResponseEntity<?> clients() {
        List<ClientResponseDTO> adminResponse =  adminService.getClients();
        return ResponseEntity.ok(adminResponse);
    }

    @GetMapping("/planes")
    public ResponseEntity<?> planes() {
        List<Plane> adminResponse =  adminService.getPlanes();
        return ResponseEntity.ok(adminResponse);

    }
}
