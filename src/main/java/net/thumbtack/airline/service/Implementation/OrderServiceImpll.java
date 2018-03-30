package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dto.request.OrderAddRequestDto;
import net.thumbtack.airline.model.Order;
import net.thumbtack.airline.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpll implements OrderService {

    @Override
    public Order add(OrderAddRequestDto requestDto) {
        return null;
    }
}
