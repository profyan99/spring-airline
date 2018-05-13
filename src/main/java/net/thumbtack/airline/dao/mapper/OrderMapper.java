package net.thumbtack.airline.dao.mapper;

import net.thumbtack.airline.model.Country;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.model.Passenger;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    List<Country> getCountries();

    void addOrder(Order order);

    void addPassenger(@Param("order") Order order, @Param("list") List<Passenger> list);

    List<Order> get(@Param("fromTown") String fromTown,
                    @Param("toTown") String toTown,
                    @Param("flightName") String flightName,
                    @Param("planeName") String planeName,
                    @Param("fromDate") String fromDate,
                    @Param("toDate") String toDate,
                    @Param("clientId") int clientId);

    Order getById(int orderId);

    void updatePassenger(Passenger passenger);
}
