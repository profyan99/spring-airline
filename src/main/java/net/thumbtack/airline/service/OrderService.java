package net.thumbtack.airline.service;

import net.thumbtack.airline.dto.OrderPlaceRegisterDto;
import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.dto.request.OrderGetParamsRequestDto;
import net.thumbtack.airline.dto.response.OrderResponseDto;
import net.thumbtack.airline.model.Country;

import java.util.List;

public interface OrderService {
    List<Country> getCountries();

    OrderResponseDto add(OrderAddRequestDto requestDto);

    List<OrderResponseDto> get(OrderGetParamsRequestDto requestDto);

    List<String> getPlaces(int orderId, int userId);

    OrderPlaceRegisterDto placeRegister(OrderPlaceRegisterDto registerDto);
}
