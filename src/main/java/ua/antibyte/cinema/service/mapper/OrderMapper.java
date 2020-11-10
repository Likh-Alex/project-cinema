package ua.antibyte.cinema.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ua.antibyte.cinema.model.Order;
import ua.antibyte.cinema.model.Ticket;
import ua.antibyte.cinema.model.dto.response.OrderResponseDto;

@Component
public class OrderMapper {
    public OrderResponseDto mapOrderToResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setUserId(order.getUser().getId());
        List<Long> ticketsId = order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        orderResponseDto.setTicketIds(ticketsId);
        return orderResponseDto;
    }
}
