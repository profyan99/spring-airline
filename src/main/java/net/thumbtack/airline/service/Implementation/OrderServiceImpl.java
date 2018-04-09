package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dao.OrderDao;
import net.thumbtack.airline.dao.UserDao;
import net.thumbtack.airline.dto.PassengerDto;
import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.dto.response.OrderAddResponseDto;
import net.thumbtack.airline.dto.response.PassengerResponseDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.model.OrderClass;
import net.thumbtack.airline.model.Passenger;
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
    public OrderAddResponseDto add(OrderAddRequestDto requestDto) {
        Flight flight = flightDao.get(requestDto.getFlightId());
        if (!flight.getDates().contains(requestDto.getDate())) {
            throw new BaseException(ErrorCode.INVALID_DATE.getErrorCodeString(), "date", ErrorCode.INVALID_DATE);
        }
        if (!flight.isApproved()) {
            throw new BaseException(ErrorCode.UNAPPROVED_FLIGHT.getErrorCodeString(), "flight", ErrorCode.UNAPPROVED_FLIGHT);
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
        return new OrderAddResponseDto(
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
}
