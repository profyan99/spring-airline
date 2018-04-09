package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.dto.response.OrderAddResponseDto;

public interface OrderService {
    OrderAddResponseDto add(OrderAddRequestDto requestDto);
}
