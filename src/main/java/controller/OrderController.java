package controller;


import dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid OrderDto orderDto) {
        orderService.configureAndAddOrder(orderDto);
    }


}
