package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.model.Order;

public interface OrderService {
    Order add(OrderAddRequestDto requestDto);
}
