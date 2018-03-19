package net.thumbtack.airline.controller;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dto.request.FlightAddRequestDTO;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDTO;
import net.thumbtack.airline.dto.request.FlightUpdateRequestDTO;
import net.thumbtack.airline.dto.response.FlightAddResponseDTO;
import net.thumbtack.airline.dto.response.FlightUpdateResponseDTO;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.CookieService;
import net.thumbtack.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/flights", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class FlightController {

    private FlightService flightService;

    private CookieService cookieService;

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Autowired
    public void setCookieService(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody FlightAddRequestDTO request,
                                 @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty() || !cookieService.getUserCookie(uuid).getUserType().equals(UserRole.ADMIN_ROLE.toString())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", "");
        }
        FlightAddResponseDTO flightResponse =  flightService.add(request);
        return ResponseEntity.ok(flightResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody FlightUpdateRequestDTO request,
                                    @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty() || !cookieService.getUserCookie(uuid).getUserType().equals(UserRole.ADMIN_ROLE.toString())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", "");
        }
        FlightUpdateResponseDTO flightResponse =  flightService.update(request);
        return ResponseEntity.ok(flightResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id,
                                    @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty() || !cookieService.getUserCookie(uuid).getUserType().equals(UserRole.ADMIN_ROLE.toString())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", "");
        }
        flightService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") int id,
                                 @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty() || !cookieService.getUserCookie(uuid).getUserType().equals(UserRole.ADMIN_ROLE.toString())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", "");
        }
        FlightAddResponseDTO flightResponse =  flightService.get(id);
        return ResponseEntity.ok(flightResponse);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable("id") int id,
                                     @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty() || !cookieService.getUserCookie(uuid).getUserType().equals(UserRole.ADMIN_ROLE.toString())) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", "");
        }
        FlightAddResponseDTO flightResponse =  flightService.approve(id);
        return ResponseEntity.ok(flightResponse);
    }

    @GetMapping
    public ResponseEntity<?> flights(
            @RequestParam(required = false, value = "fromTown", defaultValue = "") String fromTown,
            @RequestParam(required = false, value = "toTown", defaultValue = "") String toTown,
            @RequestParam(required = false, value = "flightName ", defaultValue = "") String flightName,
            @RequestParam(required = false, value = "planeName", defaultValue = "") String planeName,
            @RequestParam(required = false, value = "fromDate", defaultValue = "") String fromDate,
            @RequestParam(required = false, value = "toDate", defaultValue = "") String toDate,
            @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        if(uuid.isEmpty()) {
            throw new BaseException(ConstantsSetting.ErrorsConstants.UNAUTHORISED_ERROR.toString(), "", "");
        }

        //TODO check user role. If admin, return flight with approve and plane
        List<FlightAddResponseDTO> flightResponse =  flightService.get(new FlightGetParamsRequestDTO(
                fromTown, toTown, flightName, planeName, fromDate, toDate
        ));
        return ResponseEntity.ok(flightResponse);
    }

}
