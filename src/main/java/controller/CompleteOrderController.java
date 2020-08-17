package controller;


import dto.CompleteOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CompleteOrderService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/complete")
public class CompleteOrderController {

    private CompleteOrderService completeOrderService;

    @Autowired
    public CompleteOrderController(CompleteOrderService completeOrderService) {
        this.completeOrderService = completeOrderService;
    }

    @GetMapping("")
    public List<CompleteOrderDto> listAllCompleteOrders() {
        return completeOrderService.listAllCompleteOrders();
    }
}
