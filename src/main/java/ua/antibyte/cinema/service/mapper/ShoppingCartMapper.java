package ua.antibyte.cinema.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ua.antibyte.cinema.model.ShoppingCart;
import ua.antibyte.cinema.model.Ticket;
import ua.antibyte.cinema.model.dto.ShoppingCartResponseDto;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapShoppingCartToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        List<Long> ticketIds = shoppingCart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        shoppingCartResponseDto.setTicketIds(ticketIds);
        return shoppingCartResponseDto;
    }
}
