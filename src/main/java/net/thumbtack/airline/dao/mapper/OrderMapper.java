package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.model.Passenger;

import java.util.List;

public interface OrderMapper {

    void addOrder(Order order);

    void addPassenger(Order order);

    List<Passenger> getPassenger(int orderId);
}
