package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.dto.request.OrderGetParamsRequestDto;
import net.thumbtack.airline.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto add(OrderAddRequestDto requestDto);

    List<OrderResponseDto> get(OrderGetParamsRequestDto requestDto);
}
