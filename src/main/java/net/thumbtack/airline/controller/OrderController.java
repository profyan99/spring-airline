package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.model.UserRole;
import net.thumbtack.airline.service.OrderService;
import net.thumbtack.airline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

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
        userService.authorizeUser(uuid, UserRole.CLIENT_ROLE);
        return ResponseEntity.ok(orderService.add(request));
    }
}
