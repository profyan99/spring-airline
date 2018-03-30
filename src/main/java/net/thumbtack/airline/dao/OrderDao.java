package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Order;

public interface OrderDao {

    /**
     * Add new client flight order
     *
     * @param order {@link Order}
     * @return filled {@link Order}
     */
    Order add(Order order);
}
