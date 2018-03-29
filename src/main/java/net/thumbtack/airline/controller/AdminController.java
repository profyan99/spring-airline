package net.thumbtack.airline.controller;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dto.UserCookieDTO;
import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
import net.thumbtack.airline.dto.request.AdminUpdateRequestDTO;
import net.thumbtack.airline.dto.response.AdminResponseDTO;
import net.thumbtack.airline.dto.response.AdminUpdateResponseDTO;
import net.thumbtack.airline.dto.response.ClientResponseDTO;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.AdminService;
import net.thumbtack.airline.service.CookieService;
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

    private CookieService cookieService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value(value = "${cookie}")
    private String COOKIE;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setCookieService(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @PostMapping("/admin")
    public ResponseEntity<?> registration(@RequestBody @Valid AdminRegistrationRequestDTO reg, HttpServletResponse response) {
        AdminResponseDTO adminResponse = adminService.register(reg);
        String cookieValue = cookieService.setUserCookie(new UserCookieDTO(adminResponse.getId(), adminResponse.getUserType()));
        response.addCookie(new Cookie(COOKIE, cookieValue));
        return ResponseEntity.ok(adminResponse);
    }

    @PutMapping("/admin")
    public ResponseEntity<?> update(@RequestBody @Valid AdminUpdateRequestDTO request,
                                    @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        UserCookieDTO userCookieDTO = cookieService.getUserCookie(uuid);
        if(uuid.isEmpty() || !userCookieDTO.getUserType().equals(UserRole.ADMIN_ROLE)) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", ErrorCode.UNAUTHORISED_ERROR);
        }
        request.setId(userCookieDTO.getId());
        AdminUpdateResponseDTO adminResponse =  adminService.update(request);
        return ResponseEntity.ok(adminResponse);
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<?> clients(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty() || !cookieService.getUserCookie(uuid).getUserType().equals(UserRole.ADMIN_ROLE)) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "",  ErrorCode.UNAUTHORISED_ERROR);
        }
        List<ClientResponseDTO> adminResponse =  adminService.getClients();
        return ResponseEntity.ok(adminResponse);
    }

    @GetMapping(path = "/planes")
    public ResponseEntity<?> planes(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty() || !cookieService.getUserCookie(uuid).getUserType().equals(UserRole.ADMIN_ROLE)) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "",  ErrorCode.UNAUTHORISED_ERROR);
        }
        List<Plane> adminResponse =  adminService.getPlanes();
        return ResponseEntity.ok(adminResponse);

    }

    @DeleteMapping("/debug/clear")
    public ResponseEntity<?> clear(@CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty() || !cookieService.getUserCookie(uuid).getUserType().equals(UserRole.ADMIN_ROLE)) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "",  ErrorCode.UNAUTHORISED_ERROR);
        }
        adminService.clearDataBase();
        return ResponseEntity.ok().build();

    }

}
