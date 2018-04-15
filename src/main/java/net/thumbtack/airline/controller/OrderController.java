package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.OrderPlaceRegisterDto;
import net.thumbtack.airline.dto.UserCookieDto;
import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.dto.request.OrderGetParamsRequestDto;
import net.thumbtack.airline.dto.response.OrderResponseDto;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.OrderService;
import net.thumbtack.airline.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private UserService userService;

    private OrderService orderService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/orders")
    public ResponseEntity<?> add(@RequestBody OrderAddRequestDto request,
                                 @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {
        request.setUserId(userService.authorizeUser(uuid, UserRole.CLIENT).getId());
        return ResponseEntity.ok(orderService.add(request));
    }

    @GetMapping(path = "/orders")
    public ResponseEntity<?> get(
            @RequestParam(required = false, value = "fromTown", defaultValue = "") String fromTown,
            @RequestParam(required = false, value = "toTown", defaultValue = "") String toTown,
            @RequestParam(required = false, value = "flightName ", defaultValue = "") String flightName,
            @RequestParam(required = false, value = "planeName", defaultValue = "") String planeName,
            @RequestParam(required = false, value = "fromDate", defaultValue = "") String fromDate,
            @RequestParam(required = false, value = "toDate", defaultValue = "") String toDate,
            @RequestParam(required = false, value = "clientId", defaultValue = "0") int clientId,
            @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {

        UserCookieDto cookieDto = userService.authorizeUser(uuid);
        List<OrderResponseDto> orderResponse = orderService.get(new OrderGetParamsRequestDto(
                fromTown, toTown, flightName, planeName, fromDate, toDate, cookieDto.getUserType(), clientId, cookieDto.getId()
        ));
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping(path = "/places/{orderId}")
    public ResponseEntity<?> getPlaces(@CookieValue(value = "${cookie}", defaultValue = "") String uuid,
                                       @PathVariable("orderId") int orderId) {

        return ResponseEntity.ok(orderService.getPlaces(orderId, userService.authorizeUser(uuid, UserRole.CLIENT).getId()));
    }

    @PostMapping(path = "/places")
    public ResponseEntity<?> registry(
            @RequestBody OrderPlaceRegisterDto request,
            @CookieValue(value = "${cookie}", defaultValue = "") String uuid) {

        userService.authorizeUser(uuid, UserRole.CLIENT);
        return ResponseEntity.ok(orderService.placeRegister(request));
    }
}
