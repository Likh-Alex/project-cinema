package ua.antibyte.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.antibyte.cinema.model.Ticket;
import ua.antibyte.cinema.model.User;
import ua.antibyte.cinema.model.dto.OrderResponseDto;
import ua.antibyte.cinema.service.OrderService;
import ua.antibyte.cinema.service.ShoppingCartService;
import ua.antibyte.cinema.service.UserService;
import ua.antibyte.cinema.service.mapper.OrderMapper;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderMapper orderMapper, OrderService orderService,
                           UserService userService, ShoppingCartService shoppingCartService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/complete")
    public void complete(@RequestParam Long userId) {
        User user = userService.findById(userId);
        List<Ticket> tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping("/by-userId")
    public List<OrderResponseDto> getOrderByUserId(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.findById(userId)).stream()
                .map(orderMapper::mapOrderToResponseDto)
                .collect(Collectors.toList());
    }
}
