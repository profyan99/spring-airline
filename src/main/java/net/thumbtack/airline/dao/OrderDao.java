package net.thumbtack.airline.dao;

import net.thumbtack.airline.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    /**
     * Add new client flight order
     *
     * @param order {@link Order}
     * @return filled {@link Order}
     */
    Order add(Order order);

    /**
     * Get {@link Order} with conditions
     *
     * @param fromTown   if it isn't null, search with fromTown
     * @param toTown     if it isn't null, search with toTown
     * @param flightName if it isn't null, search with flightName
     * @param planeName  if it isn't null, search with planeName
     * @param fromDate   if it isn't null, search with fromDate
     * @param toDate     if it isn't null, search with toDate
     * @param clientId   if it isn't null, search with clientId
     * @return {@link List<Order>}
     */
    List<Order> get(String fromTown, String toTown, String flightName, String planeName, String fromDate, String toDate,
                    int clientId);

    /**
     * Get single {@link Order}
     * @param orderId id of the order
     * @return {@link Order}
     */
    Optional<Order> get(int orderId);
}
