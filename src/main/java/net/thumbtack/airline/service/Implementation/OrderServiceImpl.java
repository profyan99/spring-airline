package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dao.OrderDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.OrderPlaceRegisterDto;
import net.thumbtack.airline.dto.PassengerDto;
import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.dto.request.OrderGetParamsRequestDto;
import net.thumbtack.airline.dto.response.OrderResponseDto;
import net.thumbtack.airline.dto.response.PassengerResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.*;
import net.thumbtack.airline.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderDao orderDao;

    private FlightDao flightDao;

    private UserDao userDao;

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setFlightDao(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public OrderResponseDto add(OrderAddRequestDto requestDto) {
        Flight flight = flightDao.get(requestDto.getFlightId()).orElseThrow(() ->
                new BaseException(
                        ErrorCode.FLIGHT_NOT_FOUND.getErrorCodeString(),
                        ErrorCode.FLIGHT_NOT_FOUND.getErrorFieldString(),
                        ErrorCode.FLIGHT_NOT_FOUND)
        );
        if (!flight.getDates().contains(requestDto.getDate())
                || flight.getSchedule().getToDate().isBefore(requestDto.getDate())) {
            throw new BaseException(ErrorCode.INVALID_DATE.getErrorCodeString(),
                    ErrorCode.INVALID_DATE.getErrorFieldString(), ErrorCode.INVALID_DATE);
        }
        if (!flight.isApproved()) {
            throw new BaseException(ErrorCode.UNAPPROVED_FLIGHT.getErrorCodeString(),
                    ErrorCode.UNAPPROVED_FLIGHT.getErrorFieldString(), ErrorCode.UNAPPROVED_FLIGHT);
        }
        if (requestDto.getPassengers().isEmpty()) {
            throw new BaseException(ErrorCode.PASSENGER_NOT_FOUND.getErrorCodeString(),
                    ErrorCode.PASSENGER_NOT_FOUND.getErrorFieldString(), ErrorCode.PASSENGER_NOT_FOUND);
        }
        int upd = flightDao.reservePlaces(requestDto.getDate().toString(), flight.getId(), requestDto.getPassengers().size());
        if (upd == 0) {
            throw new BaseException(ErrorCode.NO_AVAILABLE_PLACES.getErrorCodeString(),
                    ErrorCode.NO_AVAILABLE_PLACES.getErrorFieldString(), ErrorCode.NO_AVAILABLE_PLACES);
        }
        Map<String, String> citizen = new HashMap<>();
        List<Passenger> passengers = new ArrayList<>();
        int totalPrice = 0;
        int currentPrice;

        userDao.getCountries().forEach(country -> citizen.putIfAbsent(country.getIso3166(), country.getName()));
        for (PassengerDto c : requestDto.getPassengers()) {
            currentPrice = (c.getOrderClass() == OrderClass.ECONOMY) ? (flight.getPriceEconomy()) : (flight.getPriceBusiness());
            passengers.add(new Passenger(
                    c.getFirstName(),
                    c.getLastName(),
                    c.getNationality() + "-" + citizen.get(c.getNationality()),
                    c.getPassport(),
                    c.getOrderClass(),
                    currentPrice
            ));
            totalPrice += currentPrice;
        }
        Order order = new Order(
                requestDto.getFlightId(),
                requestDto.getUserId(),
                requestDto.getDate(),
                totalPrice,
                flight.getFlightName(),
                flight.getPlaneName(),
                flight.getFromTown(),
                flight.getToTown(),
                flight.getStart(),
                flight.getDuration(),
                passengers
        );
        orderDao.add(order);
        List<PassengerResponseDto> passengerResponseDtos = new ArrayList<>(passengers.size());
        order.getPassengers().forEach(passenger ->
                passengerResponseDtos.add(new PassengerResponseDto(
                        passenger.getFirstName(),
                        passenger.getLastName(),
                        passenger.getNationality(),
                        passenger.getPassport(),
                        passenger.getOrderClass(),
                        passenger.getTicket(),
                        passenger.getPrice()
                ))
        );
        return new OrderResponseDto(
                order.getFlightId(),
                order.getDate(),
                order.getOrderId(),
                order.getTotalPrice(),
                order.getFlightName(),
                order.getPlaneName(),
                order.getFromTown(),
                order.getToTown(),
                order.getStart(),
                order.getDuration(),
                passengerResponseDtos
        );
    }

    @Override
    public List<OrderResponseDto> get(OrderGetParamsRequestDto params) {
        Utils.validateRequestParams(params);
        if (params.getUserType() == UserRole.CLIENT) {
            params.setClientId(params.getUserId());
        }
        List<Order> orders = orderDao.get(
                params.getFromTown(),
                params.getToTown(),
                params.getFlightName(),
                params.getPlaneName(),
                params.getFromDate(),
                params.getToDate(),
                params.getClientId()
        );
        List<PassengerResponseDto> passengerResponseDtos;
        List<OrderResponseDto> responseDtos = new ArrayList<>(orders.size());
        for (Order o : orders) {
            passengerResponseDtos = new ArrayList<>((o.getPassengers().size()));
            for (Passenger p : o.getPassengers()) {
                passengerResponseDtos.add(new PassengerResponseDto(
                        p.getFirstName(),
                        p.getLastName(),
                        p.getNationality(),
                        p.getPassport(),
                        p.getOrderClass(),
                        p.getTicket(),
                        p.getPrice()
                ));
            }
            responseDtos.add(new OrderResponseDto(
                    o.getFlightId(),
                    o.getDate(),
                    o.getOrderId(),
                    o.getTotalPrice(),
                    o.getFlightName(),
                    o.getPlaneName(),
                    o.getFromTown(),
                    o.getToTown(),
                    o.getStart(),
                    o.getDuration(),
                    passengerResponseDtos
            ));
        }
        return responseDtos;
    }

    @Override
    public List<String> getPlaces(int orderId, int userId) {
        Order order = orderDao.get(orderId).orElseThrow(
                () -> new BaseException(
                        ErrorCode.ORDER_NOT_FOUND.getErrorCodeString(),
                        ErrorCode.ORDER_NOT_FOUND.getErrorFieldString(),
                        ErrorCode.ORDER_NOT_FOUND)
        );
        if (order.getUserId() != userId) {
            throw new BaseException(ErrorCode.NO_ACCESS.getErrorCodeString(),
                    "Places", ErrorCode.NO_ACCESS);
        }
        List<String> places = new ArrayList<>();
        /*
         * 0 - nothing
         * 1- economy
         * 2- business
         * 3- all
         */
        int placeType = 0;
        for (Passenger p : order.getPassengers()) {
            if (p.getOrderClass() == OrderClass.BUSINESS) {
                if (placeType == 1) {
                    placeType = 3;
                } else {
                    placeType = 2;
                }
            } else {
                if (placeType == 2) {
                    placeType = 3;
                } else {
                    placeType = 1;
                }
            }
        }
        int finalPlaceType = placeType;
        flightDao.getFlightDate(order.getDate().toString(), order.getFlightId()).getPlaces().forEach((p) ->
                {
                    if (p.isFree()) {
                        String place = "";
                        switch (finalPlaceType) {
                            case 1: {
                                if (p.getType() == OrderClass.ECONOMY) {
                                    place = p.getRow() + p.getPlace();
                                }
                                break;
                            }
                            case 2: {
                                if (p.getType() == OrderClass.BUSINESS) {
                                    place = p.getRow() + p.getPlace();
                                }
                                break;
                            }
                            default: {
                                place = p.getRow() + p.getPlace();
                                break;
                            }
                        }
                        places.add(place);
                    }

                }
        );
        return places;
    }

    @Override
    public OrderPlaceRegisterDto placeRegister(OrderPlaceRegisterDto registerDto) {
        Order order = orderDao.get(registerDto.getOrderId()).orElseThrow(
                () -> new BaseException(
                        ErrorCode.ORDER_NOT_FOUND.getErrorCodeString(),
                        ErrorCode.ORDER_NOT_FOUND.getErrorFieldString(),
                        ErrorCode.ORDER_NOT_FOUND)
        );
        Passenger passenger = null;
        for (Passenger p : order.getPassengers()) {
            if (p.getTicket() == registerDto.getTicket()
                    && p.getFirstName().equals(registerDto.getFirstName())
                    && p.getLastName().equals(registerDto.getLastName())) {
                passenger = p;
            }
        }
        if (passenger == null) {
            throw new BaseException(ErrorCode.PASSENGER_NOT_FOUND.getErrorCodeString(),
                    ErrorCode.PASSENGER_NOT_FOUND.getErrorFieldString(), ErrorCode.PASSENGER_NOT_FOUND);
        }
        String reqPlace = registerDto.getPlace();
        if (reqPlace.length() < 2) {
            throw new BaseException(ErrorCode.INVALID_PLACE.getErrorCodeString(),
                    ErrorCode.INVALID_PLACE.getErrorFieldString(), ErrorCode.INVALID_PLACE);
        }
        String placeStr = reqPlace.substring(reqPlace.length() - 1);
        int row;
        try {
            row = Integer.parseInt(reqPlace.substring(0, reqPlace.length() - 1));
        } catch (NumberFormatException e) {
            logger.error("Error while parsing integer of place: " + reqPlace);
            throw new BaseException(ErrorCode.INVALID_PLACE.getErrorCodeString(),
                    ErrorCode.INVALID_PLACE.getErrorFieldString(), ErrorCode.INVALID_PLACE);
        }
        Place place = flightDao.getPlace(order.getDate().toString(), order.getFlightId(), placeStr, row).orElseThrow(() ->
                new BaseException(
                        ErrorCode.PLACE_NOT_FOUND.getErrorCodeString(),
                        ErrorCode.PLACE_NOT_FOUND.getErrorFieldString(),
                        ErrorCode.PLACE_NOT_FOUND)
        );
        if (place.getType() != passenger.getOrderClass()) {
            throw new BaseException(ErrorCode.INVALID_PLACE.getErrorCodeString(),
                    ErrorCode.INVALID_PLACE.getErrorFieldString(), ErrorCode.INVALID_PLACE);
        }
        if (!place.isFree()) {
            throw new BaseException(ErrorCode.PLACE_OCCUPIED.getErrorCodeString(),
                    ErrorCode.PLACE_OCCUPIED.getErrorFieldString(), ErrorCode.PLACE_OCCUPIED);
        }
        flightDao.updatePlace(order.getDate().toString(), order.getFlightId(), placeStr, row);
        return registerDto;
    }
}
