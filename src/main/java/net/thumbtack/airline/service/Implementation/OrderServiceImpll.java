package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dao.OrderDao;
import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.exception.BaseException;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpll implements OrderService {

    private OrderDao orderDao;

    private FlightDao flightDao;

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setFlightDao(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public Order add(OrderAddRequestDto requestDto) {
        Flight flight = flightDao.get(requestDto.getFlightId());
        if (!flight.getDates().contains(requestDto.getDate())) {
            throw new BaseException(ErrorCode.INVALID_DATE.getErrorCodeString(), "Date", ErrorCode.INVALID_DATE);
        }

        return null;
    }
}
