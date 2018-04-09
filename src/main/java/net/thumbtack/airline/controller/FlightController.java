package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.request.FlightAddRequestDto;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDto;
import net.thumbtack.airline.dto.request.FlightUpdateRequestDto;
import net.thumbtack.airline.dto.response.FlightGetResponseDto;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.FlightService;
import net.thumbtack.airline.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/flights", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class FlightController {

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    private FlightService flightService;

    private UserService userService;

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody FlightAddRequestDto request,
                                 @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        userService.authorizeUser(uuid, UserRole.ADMIN);
        return ResponseEntity.ok(flightService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody FlightUpdateRequestDto request,
                                    @CookieValue(value = "${cookie}", defaultValue = "") String uuid,
                                    @PathVariable(value = "id") int flightId) {
        userService.authorizeUser(uuid, UserRole.ADMIN);
        request.setId(flightId);
        return ResponseEntity.ok(flightService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id,
                                    @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        userService.authorizeUser(uuid, UserRole.ADMIN);
        flightService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable("id") int id,
                                 @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        userService.authorizeUser(uuid, UserRole.ADMIN);
        return ResponseEntity.ok(flightService.get(id));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable("id") int id,
                                     @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        userService.authorizeUser(uuid, UserRole.ADMIN);
        return ResponseEntity.ok(flightService.approve(id));
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

        List<FlightGetResponseDto> flightResponse = flightService.get(new FlightGetParamsRequestDto(
                fromTown, toTown, flightName, planeName, fromDate, toDate, userService.authorizeUser(uuid).getUserType()
        ));
        return ResponseEntity.ok(flightResponse);
    }

}
