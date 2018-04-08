package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.request.AdminRegistrationRequestDto;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDto;
import net.thumbtack.airline.dto.response.AdminResponseDto;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.AdminService;
import net.thumbtack.airline.service.UserService;
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

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private AdminService adminService;

    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value(value = "${cookie}")
    private String COOKIE;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin")
    public ResponseEntity<?> registration(@RequestBody @Valid AdminRegistrationRequestDto reg, HttpServletResponse response) {
        AdminResponseDto adminResponse = adminService.register(reg);
        String cookieValue = userService.setUserCookie(new UserCookieDto(adminResponse.getId(), adminResponse.getUserType()));
        response.addCookie(new Cookie(COOKIE, cookieValue));
        return ResponseEntity.ok(adminResponse);
    }

    @PutMapping("/admin")
    public ResponseEntity<?> update(@RequestBody @Valid AdminUpdateRequestDto request,
                                    @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        request.setId(userService.authorizeUser(uuid, UserRole.ADMIN_ROLE).getId());
        return ResponseEntity.ok(adminService.update(request));
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<?> clients(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        userService.authorizeUser(uuid, UserRole.ADMIN_ROLE);
        return ResponseEntity.ok(adminService.getClients());
    }

    @GetMapping(path = "/planes")
    public ResponseEntity<?> planes(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        userService.authorizeUser(uuid, UserRole.ADMIN_ROLE);
        return ResponseEntity.ok(adminService.getPlanes());
    }

    @DeleteMapping("/debug/clear")
    public ResponseEntity<?> clear(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        userService.authorizeUser(uuid, UserRole.ADMIN_ROLE);
        adminService.clearDataBase();
        return ResponseEntity.ok().build();
    }

}
